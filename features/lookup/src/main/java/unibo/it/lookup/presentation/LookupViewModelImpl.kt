package unibo.it.lookup.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.juul.kable.Advertisement
import com.juul.kable.Scanner
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import unibo.it.common.utils.cancelChildren
import unibo.it.common.utils.childScope
import unibo.it.lookup_api.model.Device
import unibo.it.lookup_api.presentation.LookupState
import unibo.it.lookup_api.presentation.LookupViewModel
import unibo.it.lookup_api.presentation.ScanState
import java.util.concurrent.TimeUnit
import kotlin.coroutines.cancellation.CancellationException

private val SCAN_DURATION_MILLIS = TimeUnit.SECONDS.toMillis(5)

/**
 * See: https://github.com/JuulLabs/sensortag/blob/main/app/src/androidMain/kotlin/features/scan/ScanViewModel.kt
 */
internal class LookupViewModelImpl(application: Application) :
    LookupViewModel(application = application) {
    private val scanner = Scanner()
    private val scanScope = viewModelScope.childScope()
    private val found = hashMapOf<String, Advertisement>()
    private val _lookupState = MutableStateFlow<LookupState>(LookupState.Action)
    private val _scanState = MutableStateFlow<ScanState>(ScanState.Stopped)
    private val _advertisements = MutableStateFlow<List<Advertisement>>(emptyList())

    override val lookupState: StateFlow<LookupState>
        get() = _lookupState

    override val scanState: StateFlow<ScanState>
        get() = _scanState

    override val advertisements: StateFlow<List<Advertisement>>
        get() = _advertisements


    override fun handleScan() {
        startScan().invokeOnCompletion {
            Log.i("SK8", "SCAN STOPPED")
            stop()
            _lookupState.value = LookupState.Found
        }
    }

    override fun setDeviceByName(name: String) {
        val advertisement = _advertisements.value.first { !it.name?.equals(name)!! }
        // TODO: add lookup repository and finish this
    }

    private fun startScan(): Job {
        if (_scanState.value == ScanState.Scanning) {
            return scanScope.launch {} // Scan already in progress.
        }

        _scanState.value = ScanState.Scanning

        return scanScope.launch {
            withTimeoutOrNull(SCAN_DURATION_MILLIS) {
                scanner
                    .advertisements
                    .catch { cause ->
                        Log.e("SK8", "${cause.message}")
                        _scanState.value = ScanState.Failed(cause.message ?: "Unknown error")
                    }
                    .onCompletion { cause ->
                        if (cause == null || cause is CancellationException) {
                            _scanState.value = ScanState.Stopped
                            Log.e("SK8A", "$cause")
                        }
                    }
                    .filter { it.isSK8 }
                    .collect { advertisement ->
                        found[advertisement.address] = advertisement
                        _advertisements.value = found.values.toList()
                    }
            }
        }
    }

    private fun stop() {
        scanScope.cancelChildren()
    }

    private fun clear() {
        _advertisements.value = emptyList()
    }

    override fun changeState(lookupState: LookupState) {
        _lookupState.value = lookupState
    }

    private val Advertisement.isSK8
        get() = name?.startsWith("sk8") == true
}
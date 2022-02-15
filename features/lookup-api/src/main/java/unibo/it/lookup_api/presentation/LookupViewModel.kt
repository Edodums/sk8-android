package unibo.it.lookup_api.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.juul.kable.Advertisement
import kotlinx.coroutines.flow.StateFlow
import unibo.it.lookup_api.model.Device

/**
 * ViewModel responsible to handle the scan for the
 * devices and for setting the chosen one
 */
abstract class LookupViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Gets the lookup state
     */
    abstract val lookupState: StateFlow<LookupState>

    /**
     * Gets the scan state
     */
    abstract val scanState: StateFlow<ScanState>

    /**
     * Gets the list of peripheral devices
     */
    abstract val advertisements: StateFlow<List<Advertisement>>

    /**
     * The regular implementation will use the BLE Android library to look for the devices
     */
    abstract fun handleScan()

    /**
     * After the user has chosen in the view the "Device" this method takes the right device sends the data to the datasource
     */
    abstract fun setDeviceByName(name: String)

    /**
     * Handles changes of states of the lookup
     */
    abstract fun changeState(lookupState: LookupState)
}
package unibo.it.menu.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.juul.kable.ConnectionLostException
import com.juul.kable.State
import com.juul.kable.peripheral
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import unibo.it.common.ble.getBluetoothManager
import unibo.it.domain.repository.MenuRepository
import unibo.it.menu_api.presentation.MenuState
import unibo.it.menu_api.presentation.MenuViewModel

@OptIn(ExperimentalCoroutinesApi::class)
internal class MenuViewModelImpl(
    application: Application,
    private val menuRepository: MenuRepository,
) : MenuViewModel(application) {
    private val mtu = 23
    private val _menuState = MutableStateFlow<MenuState>(MenuState.NotPaired)
    override val menuState: StateFlow<MenuState>
        get() = _menuState

    init {
        viewModelScope.launch {
            val lastDevice = menuRepository.getLastDevice()
            // Not the nicest solution but at least I've avoided the Android Room Type Converter problem
            val bluetoothAdapter = getBluetoothManager(application.applicationContext).adapter

            lastDevice?.address?.let {
                val peripheral = CoroutineScope(Dispatchers.IO).peripheral(
                    bluetoothDevice = bluetoothAdapter.getRemoteDevice(it)
                ) {
                    onServicesDiscovered {
                        requestMtu(mtu)
                    }
                }

                try {
                    peripheral.connect()
                } catch (cause: ConnectionLostException) {
                    Log.e("SK8", "$cause")
                } catch (ex: Exception) {
                    Log.e("SK8", "$ex")
                }

                peripheral.state.value.let { state ->
                    when (state) {
                        State.Connected -> {
                            lastDevice.let { device ->
                                device.isConnected = true
                                menuRepository.updateDevice(device)
                                _menuState.value = MenuState.Paired
                            }
                        }
                        State.Connecting.Bluetooth -> {
                            Log.i("SK8", "Connecting device to Bluetooth")
                        }
                        State.Connecting.Services -> {
                            Log.i("SK8", "Connecting device to Services")
                        }
                        State.Connecting.Observes -> {
                            Log.i("SK8", "Connecting device to Observes")
                        }
                        State.Disconnecting -> {
                            Log.i("SK8", "Disconnecting device")
                        }
                        is State.Disconnected -> {
                            Log.i("SK8", "Disconnected device")
                            lastDevice.let { device ->
                                device.isConnected = false
                                menuRepository.updateDevice(device)
                            }
                            _menuState.value = MenuState.NotPaired
                        }
                    }
                }
            }
        }
    }
}
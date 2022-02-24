package unibo.it.controls.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.juul.kable.ConnectionLostException
import com.juul.kable.Peripheral
import com.juul.kable.State
import com.juul.kable.WriteType
import com.juul.kable.peripheral
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import unibo.it.common.ble.enableAutoReconnect
import unibo.it.common.ble.getBluetoothManager
import unibo.it.common.ble.writeCharacteristic
import unibo.it.controls.model.Control
import unibo.it.controls_api.presentation.ControlsViewModel
import unibo.it.domain.repository.MenuRepository

internal class ControlsViewModelImpl(
    application: Application,
    menuRepository: MenuRepository
) : ControlsViewModel(application = application) {
    private val mtu = 23
    private lateinit var peripheral: Peripheral
    private var prevSpeedCommand: Control.SpeedCommand = Control.SpeedCommand.Stop

    init {
        viewModelScope.launch {
            val lastDevice = menuRepository.getLastDevice()
            val bluetoothAdapter = getBluetoothManager(application.applicationContext).adapter

            lastDevice?.address?.let {
                peripheral = CoroutineScope(Dispatchers.IO).peripheral(
                    bluetoothDevice = bluetoothAdapter.getRemoteDevice(it)
                ) {
                    onServicesDiscovered {
                        requestMtu(mtu)
                    }
                }

                try {
                    peripheral.connect()
                    viewModelScope.enableAutoReconnect(peripheral, viewModelScope)
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
                        }
                    }
                }
            }
        }
    }

    /**
     * It actually has an error due to a poor decision:
     *  If you drag up for more than 3 seconds it start to go up again the value ( in a range 0-20 )
     *  For now 0-20 is Forward, a value 21-100 is Stop, 100-150 is Reverse
     */
    override fun speedHandler(value: Float) {
        var speedCommand: Control.SpeedCommand = Control.SpeedCommand.Stop
        val convertedToIntValue = value.toInt()

        if (convertedToIntValue in 0..20) {
            speedCommand = Control.SpeedCommand.Forward
        }

        if (convertedToIntValue in 21..100) {
            speedCommand = Control.SpeedCommand.Stop
        }

        if (convertedToIntValue in 100..150) {
            speedCommand = Control.SpeedCommand.Reverse
        }

        if (speedCommand == prevSpeedCommand) {
            return
        }

        prevSpeedCommand = speedCommand
        val control = Control(value = speedCommand)

        try {

            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    peripheral.write(
                        writeCharacteristic,
                        Json.encodeToString(control).toByteArray(),
                        WriteType.WithResponse
                    )
                }
            }

        } catch (e: Exception) {
            Log.e("Sk8", "Could send data to the device")
        }
    }
}
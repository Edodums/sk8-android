package unibo.it.settings.presentation

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import unibo.it.common.ble.enableAutoReconnect
import unibo.it.common.ble.getBluetoothManager
import unibo.it.common.ble.writeCharacteristic
import unibo.it.domain.model.Setting
import unibo.it.domain.repository.MenuRepository
import unibo.it.domain.repository.SettingRepository
import unibo.it.settings.mapper.SettingMapper
import unibo.it.settings_api.presentation.DefaultSettingState
import unibo.it.settings_api.presentation.EscCommand
import unibo.it.settings_api.presentation.SettingsViewModel
import kotlin.collections.set
import unibo.it.settings.model.Setting as APISetting


internal class SettingsViewModelImpl(
    application: Application,
    private val settingRepository: SettingRepository,
    private val menuRepository: MenuRepository,
    private val settingMapper: SettingMapper
) : SettingsViewModel(application = application) {
    private val mtu = 23
    private val settings: HashMap<String, String> = hashMapOf()
    private lateinit var peripheral: Peripheral

    init {
        viewModelScope.launch {
            val lastDevice = menuRepository.getLastDevice()
            // Not the nicest solution but at least I've avoided the Android Room Type Converter problem
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

    override fun loadDefaults(): Flow<DefaultSettingState> = flow {
        settingRepository.loadDefaults().collect { settings ->
            if (settings.isNotEmpty()) {
                val mappedSettings = settingMapper.toView(settings)
                emit(DefaultSettingState.Loaded(mappedSettings))
            } else {
                emit(DefaultSettingState.Loading)
            }
        }
    }

    override fun getEscCommand(): List<EscCommand> {
        return EscCommand.values().toList()
    }

    override fun commandValueChanged(commandName: String, option: String) {
        settings[commandName] = option
    }

    override fun saveChanges() {
        viewModelScope.launch {
            val changes = settings.map { entry ->
                Setting(entry.key, entry.value)
            }

            settingRepository.saveChanges(changes)
        }
    }


    override fun sendChangesToESP(): String {
        return try {
            settings.map { entry ->
                APISetting(entry.key, entry.value)
            }.forEach {
                CoroutineScope(Dispatchers.IO).launch {
                    runCatching {
                        peripheral.write(
                            writeCharacteristic,
                            Json.encodeToString(it).toByteArray(),
                            WriteType.WithResponse
                        )
                    }
                }
            }

            "All Done"
        } catch (e: Exception) {
            "Could send data to the device"
        }
    }
}

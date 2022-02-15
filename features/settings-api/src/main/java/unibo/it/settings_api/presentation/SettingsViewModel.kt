package unibo.it.settings_api.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * mapped setting data to be used by the ui
     */
    abstract fun loadDefaults(): Flow<DefaultSettingState>

    /**
     * Just get the default options of the ESC
     */
    abstract fun getEscCommand(): List<EscCommand>

    /**
     * Updates the HashMap<String(from EscCommand),String (from EscCommandOption)>
     */
    abstract fun commandValueChanged(commandName: String, option: String)

    /**
     *  uses the private hashMap then sends it
     */
    abstract fun saveChanges()

    /**
     *
     */
    abstract fun sendChangesToESP(): String
}
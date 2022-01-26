package unibo.it.lookup_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import unibo.it.lookup_api.model.Device

/**
 * ViewModel responsible to handle the scan for the
 * devices and for setting the chosen one
 */
abstract class LookupViewModel : ViewModel() {
    /**
     * Gets the lookup state
     */
    abstract fun loadLookupState(): Flow<LookupState>

    /**
     * The regular implementation will use the BLE Android library to look for the devices
     */
    abstract fun handleScan()

    /**
     * After the user has chosen in the view the "Device" this method sends the data to the datasource
     */
    abstract fun setDevice(data: Device)

    /**
     * Handles changes of states of the lookup
     */
    abstract fun changeState(lookupState: LookupState)
}
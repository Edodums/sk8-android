package unibo.it.lookup.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import unibo.it.lookup_api.model.Device
import unibo.it.lookup_api.presentation.LookupState
import unibo.it.lookup_api.presentation.LookupViewModel

internal class LookupViewModelImpl: LookupViewModel() {
    private val _lookupState = MutableStateFlow<LookupState>(LookupState.Action)
    private val lookupState : StateFlow<LookupState> = _lookupState

    override fun loadLookupState(): Flow<LookupState> = flow {
        lookupState
    }

    override fun handleScan() {
        TODO("Not yet implemented")
    }

    override fun setDevice(data: Device) {
        TODO("Not yet implemented")
    }

    override fun changeState(lookupState: LookupState) {
        _lookupState.value = lookupState
    }
}
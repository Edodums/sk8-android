package unibo.it.lookup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LookupViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: LookupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LookupState>(LookupState.Action)
    val uiState: StateFlow<LookupState> = _uiState

    fun changeState(state: LookupState) {
        // to avoid problems just handle screen as features
        _uiState.value = state
    }
}

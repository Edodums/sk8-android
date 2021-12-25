package unibo.it.sk8.lookup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LookupViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: LookupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LookupState>(LookupState.Loading)
    val uiState: StateFlow<LookupState> = _uiState


}

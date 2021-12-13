package unibo.it.sk8.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import unibo.it.sk8.database.Preferences
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    preferences: Preferences
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserState>(UserState.Loading)
    val uiState: StateFlow<UserState> = _uiState

    init {
        viewModelScope.launch {
            preferences.getUser().collect {
                it?.let {
                    _uiState.value = UserState.Authenticated
                } ?: run {
                    _uiState.value = UserState.NotAuthenticated
                }
            }
        }
    }
}

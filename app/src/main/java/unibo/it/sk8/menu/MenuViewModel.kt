package unibo.it.sk8.menu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MenuRepository
): ViewModel() {
    private val _menuState = MutableStateFlow<MenuState>(MenuState.NotPaired)
    val menuState: StateFlow<MenuState> = _menuState

}

package unibo.it.menu_api.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class MenuViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *
     */
    abstract val menuState: StateFlow<MenuState>
}
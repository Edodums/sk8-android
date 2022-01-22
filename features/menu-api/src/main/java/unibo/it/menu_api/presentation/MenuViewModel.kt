package unibo.it.menu_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class MenuViewModel : ViewModel() {
    abstract fun isDeviceConnected(): Flow<Boolean>
}
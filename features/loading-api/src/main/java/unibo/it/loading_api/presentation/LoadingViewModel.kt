package unibo.it.loading_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class LoadingViewModel : ViewModel() {
    /**
     * Get user state
     */
    abstract fun loadUserState() : Flow<UserState>
}
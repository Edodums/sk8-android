package unibo.it.loading_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class LoadingViewModel : ViewModel() {
    /**
     * Handles user state
     */
    abstract val userState : Flow<UserState>
}
package unibo.it.loading_api.presentation

sealed class UserState(
    private val isLoading: Boolean = true,
    private val isAuthenticated: Boolean = false
) {
    object Loading : UserState(isLoading = true)
    object Authenticated : UserState(isAuthenticated = true)
    object NotAuthenticated : UserState(isAuthenticated = false)
}


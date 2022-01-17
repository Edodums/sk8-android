package unibo.it.sk8.loading

sealed class UserState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = false
) {
    object Loading : UserState(isLoading = true)
    object Authenticated : UserState(isAuthenticated = true)
    object NotAuthenticated : UserState(isAuthenticated = false)
}


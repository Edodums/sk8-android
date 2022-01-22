<<<<<<<< HEAD:features/loading-api/src/main/java/unibo/it/loading_api/presentation/UserState.kt
package unibo.it.loading_api.presentation
========
package unibo.it.sk8.loading
>>>>>>>> main:app/src/main/java/unibo/it/sk8/loading/UserState.kt

sealed class UserState(
    private val isLoading: Boolean = true,
    private val isAuthenticated: Boolean = false
) {
    object Loading : UserState(isLoading = true)
    object Authenticated : UserState(isAuthenticated = true)
    object NotAuthenticated : UserState(isAuthenticated = false)
}


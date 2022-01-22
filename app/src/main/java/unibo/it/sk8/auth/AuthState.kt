<<<<<<<< HEAD:features/auth-api/src/main/java/unibo/it/auth_api/presentation/AuthState.kt
package unibo.it.auth_api.presentation
========
package unibo.it.sk8.auth
>>>>>>>> main:app/src/main/java/unibo/it/sk8/auth/AuthState.kt

sealed class AuthState(
    val isSign: Boolean = true,
    val isOTP: Boolean = false,
    val isVerified: Boolean = false
) {
    object Sign : AuthState(isSign = true)
    object OTP : AuthState(isOTP = true)
    object Verified : AuthState(isVerified = false)
}

package unibo.it.auth_api.presentation

sealed class AuthState(
    val isSign: Boolean = true,
    val isOTP: Boolean = false,
    val isVerified: Boolean = false
) {
    object Sign : AuthState(isSign = true)
    object OTP : AuthState(isOTP = true)
    object Verified : AuthState(isVerified = false)
}

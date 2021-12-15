package unibo.it.sk8.auth

sealed class AuthState(
    val isSign: Boolean = true,
    val isOTP: Boolean = false,
    val isVerified: Boolean = false
) {
    object Sign : AuthState(isSign = true)
    object OTP : AuthState(isOTP = true)
    object Verified : AuthState(isVerified = false)
}

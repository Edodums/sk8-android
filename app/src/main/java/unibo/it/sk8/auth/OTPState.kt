<<<<<<<< HEAD:app/src/main/java/unibo/it/sk8/auth/otp/OTPState.kt
package unibo.it.auth.presentation.textfield.otp
========
package unibo.it.sk8.auth
>>>>>>>> main:app/src/main/java/unibo/it/sk8/auth/OTPState.kt

import unibo.it.auth.presentation.textfield.TextFieldState

internal class OTPState :
    TextFieldState(validator = ::isOTPCharValid, errorFor = ::otpCharValidationError)

private fun isOTPCharValid(text: String): Boolean {
    return text.trim().length in 1..1
}

@Suppress("UNUSED_PARAMETER")
private fun otpCharValidationError(text: String): String {
    return "Invalid OTP Char"
}
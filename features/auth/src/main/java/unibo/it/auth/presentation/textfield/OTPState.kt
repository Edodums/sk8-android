package unibo.it.auth.presentation.textfield

class OTPState :
    TextFieldState(validator = ::isOTPCharValid, errorFor = ::otpCharValidationError)

private fun isOTPCharValid(text: String): Boolean {
    return text.trim().length in 1..1
}

@Suppress("UNUSED_PARAMETER")
private fun otpCharValidationError(text: String): String {
    return "Invalid OTP Char"
}


sealed class OTPStates(
    val isValid: Boolean = false,
    val data: MutableList<OTPState> = mutableListOf()
) {
    object Success : OTPStates(isValid = true)
    object StillNot: OTPStates()
}

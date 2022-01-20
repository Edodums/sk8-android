package unibo.it.auth.presentation.textfield.otp

sealed class OTPStates(
    val isValid: Boolean = false,
    val data: MutableList<OTPState> = mutableListOf()
) {
    object Success : OTPStates(isValid = true)
    object StillNot: OTPStates()
}

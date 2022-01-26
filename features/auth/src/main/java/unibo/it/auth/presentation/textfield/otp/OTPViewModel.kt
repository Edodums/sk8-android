package unibo.it.auth.presentation.textfield.otp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class OTPViewModel : ViewModel() {
    private var _otp: MutableList<String> = MutableList(size = Constant.OTP_CHARS) { "" }
    private var otp: String = ""

    private val _otpStates = MutableStateFlow<OTPStates>(OTPStates.StillNot)
    val otpStates: StateFlow<OTPStates>
        get() = _otpStates

    // TODO: resend notification

    /**
     * add char to the OTP string
     */
    fun addOTPChar(index: Int, text: String) {
        _otp[index] = text

        val currentOTP = _otp.filter { it.isNotEmpty() }.joinToString(separator = "")

        if (currentOTP.length == Constant.OTP_CHARS) {
            _otpStates.value = OTPStates.Success
            otp = currentOTP
        }
    }

    /**
     * gets the OTP string that
     */
    fun getOTP(): String {
        return otp
    }
}
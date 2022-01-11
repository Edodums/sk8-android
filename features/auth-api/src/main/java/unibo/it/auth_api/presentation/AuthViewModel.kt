package unibo.it.auth_api.presentation

import androidx.lifecycle.ViewModel

/**
 * ViewModel responsible to verify, signIn/Up and contains the OTP string state
 */
abstract class AuthViewModel : ViewModel() {

    /**
     * Verifies that the otp code is actually right
     */
    abstract suspend fun verify(code: String)

    /**
     * Exactly what it says
     */
    abstract suspend fun signIn(email: String, password: String)

    /**
     * add char to the OTP string
     */
    abstract fun addOTPChar(index: Int, text: String)

    /**
     * gets the OTP string that
     */
    abstract fun getOTP(): String
}
package unibo.it.auth_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel responsible to verify, signIn/Up and contains the OTP string state
 */
abstract class AuthViewModel : ViewModel() {

    /**
     * Get auth state
     */
    abstract fun loadAuthState() : Flow<AuthState>

    /**
     * Verifies that the otp code is actually right
     */
    abstract suspend fun verify(code: String)

    /**
     * Exactly what it says
     */
    abstract suspend fun signIn(email: String, password: String)
}
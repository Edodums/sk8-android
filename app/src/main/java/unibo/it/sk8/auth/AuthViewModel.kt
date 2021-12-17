package unibo.it.sk8.auth

import com.amplifyframework.core.Amplify as AmplifyBacks
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.kotlin.core.Amplify
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import unibo.it.sk8.utils.Constant

@ExperimentalCoroutinesApi
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AuthRepository
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Sign)
    val authState: StateFlow<AuthState> = _authState

    private val _otpStates = MutableStateFlow<OTPStates>(OTPStates.StillNot)
    val otpStates: StateFlow<OTPStates> = _otpStates

    private val _lastEmail = MutableLiveData<String>()
    private val lastEmail: LiveData<String> = _lastEmail

    private var _otp: MutableList<String> = MutableList(size = Constant.OTP_CHARS) { "" }
    private var otp: String = ""

    init {
        AmplifyBacks.Hub.subscribe(HubChannel.AUTH) { event ->
            when (AuthChannelEventName.valueOf(event.name)) {
                AuthChannelEventName.SIGNED_IN -> {
                    _authState.value = AuthState.Verified
                    Log.i("AuthQuickstart", "Auth just became signed in")
                }
                AuthChannelEventName.SIGNED_OUT -> {
                    _authState.value = AuthState.Sign
                    Log.i("AuthQuickstart", "Auth just became signed out")
                }
                AuthChannelEventName.SESSION_EXPIRED -> {
                    _authState.value = AuthState.Sign
                    Log.i("AuthQuickstart", "Auth session just expired")
                }
            }
        }
    }

    suspend fun verify(code: String) {
        try {
            val result = Amplify.Auth.confirmSignUp(lastEmail.value.toString(), code)

            if (result.isSignUpComplete) {
                _authState.value = AuthState.Verified
                Log.i("AuthQuickstart", "Signup confirmed")
            } else {
                Log.i("AuthQuickstart", "Signup confirmation not yet complete")
            }
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Failed to confirm signup", error)
        }
    }

    suspend fun signIn(email: String, password: String) {
        _lastEmail.value = email

        try {
            val result = Amplify.Auth.signIn(email, password)
            if (result.isSignInComplete) {
                Log.i("AuthQuickstart", "Sign in succeeded")
            } else {
                Log.e("AuthQuickstart", "Sign in not complete")
            }
        } catch (error: AuthException.UserNotConfirmedException) {
            _authState.value = AuthState.OTP
        } catch (error: AuthException.UserNotFoundException) {
            signUp(email, password)
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Sign in failed", error)
        }

    }

    private suspend fun signUp(email: String, password: String) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()

        try {
            val result = Amplify.Auth.signUp(email, password, options)
            _authState.value = AuthState.OTP
            Log.i("AuthQuickStart", "Result: $result")
        } catch (error: AuthException) {
            Log.e("AuthQuickStart", "Sign up failed", error)
        }
    }

    fun addOTPChar(index: Int, text: String) {
        _otp[index] = text

        val currentOTP = _otp.filter { it.isNotEmpty() }.joinToString(separator = "")

        if (currentOTP.length == Constant.OTP_CHARS) {
            _otpStates.value = OTPStates.Success
            otp = currentOTP
        }
    }

    fun getOTP(): String {
        return otp
    }
}

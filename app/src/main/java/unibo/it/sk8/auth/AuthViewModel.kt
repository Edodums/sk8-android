package unibo.it.sk8.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.amplifyframework.hub.HubChannel
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

    private var _otp: MutableList<String> = mutableListOf()
    private var otp: String = ""

    init {
        Amplify.Hub.subscribe(HubChannel.AUTH) { event ->
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

    fun verify(code: String) {
        Amplify.Auth.confirmSignUp(lastEmail.value.toString(), code,
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
                } else {
                    Log.i("AuthQuickstart", "Confirm sign up not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
        )
    }

    fun signIn(email: String, password: String) {
        _lastEmail.value = email

        Amplify.Auth.signIn(email, password,
            { Log.i("AuthQuickStart", "Sign in succeeded: $it") },
            {
                Log.e("AuthQuickStart", "Sign in failed", it)
                signUp(email, password)
            }
        )
    }

    private fun signUp(email: String, password: String) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()

        Amplify.Auth.signUp(email, password, options,
            {
                Log.i("AuthQuickStart", "Sign up succeeded: $it")
                _authState.value = AuthState.OTP
            },
            { Log.e("AuthQuickStart", "Sign up failed", it) }
        )
    }

    fun addOTPChar(index: Int, text: String) {
        _otp.add(index, text)

        val currentOTP = _otp.filter { it.isEmpty() }.joinToString()

        if (currentOTP.length == Constant.OTP_CHARS) {
            _otpStates.value = OTPStates.Success
            otp = currentOTP
        }
    }

    fun getOTP(): String {
        return otp
    }
}

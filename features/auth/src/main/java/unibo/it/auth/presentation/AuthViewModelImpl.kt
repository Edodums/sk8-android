package unibo.it.auth.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import unibo.it.auth_api.presentation.AuthState
import unibo.it.auth_api.presentation.AuthViewModel
import unibo.it.domain.model.UserData
import unibo.it.domain.repository.AuthRepository
import com.amplifyframework.core.Amplify as AmplifyBacks

@ExperimentalCoroutinesApi
internal class AuthViewModelImpl constructor(
    private val repository: AuthRepository
) : AuthViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Sign)
    private val _lastEmail = MutableLiveData<String>()
    private val lastEmail: LiveData<String> = _lastEmail

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

    suspend fun saveToken(token: String, email: String) {
        viewModelScope.launch {
            repository.saveToken(
                UserData(token, email)
            )
        }
    }

    override fun loadAuthState(): Flow<AuthState> = flow { _authState }

    override suspend fun verify(code: String) {
        try {
            val result = Amplify.Auth.confirmSignUp(lastEmail.value.toString(), code)

            if (result.isSignUpComplete) {
                _authState.value = AuthState.Verified
                Log.i("AuthQuickstart", "Signup confirmed: $result")
            } else {
                Log.i("AuthQuickstart", "Signup confirmation not yet complete")
            }
        } catch (error: AuthException) {
            Log.e("AuthQuickstart", "Failed to confirm signup", error)
        }
    }

    override suspend fun signIn(email: String, password: String) {
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
}

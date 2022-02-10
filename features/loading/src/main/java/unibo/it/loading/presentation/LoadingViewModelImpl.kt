package unibo.it.loading.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.core.Amplify
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import unibo.it.domain.model.UserData
import unibo.it.domain.repository.AuthRepository
import unibo.it.loading_api.presentation.LoadingViewModel
import unibo.it.loading_api.presentation.UserState

@InternalCoroutinesApi
class LoadingViewModelImpl constructor(
    private val authRepository: AuthRepository
) : LoadingViewModel() {
    private val _token = MutableStateFlow<String>("")
    private val token: StateFlow<String> = _token

    private val _email = MutableStateFlow<String>("")
    private val email: StateFlow<String> = _email

    init {
        loadToken()
        loadEmail()
    }

    override val userState: Flow<UserState> = flow {
        var userData = authRepository.loadData().first()

        if (check(userData)) {
            emit(UserState.Authenticated)
        } else {
            email.collect { emailValue ->
                token.collect { tokenValue ->
                    userData = UserData(
                        token = tokenValue,
                        email = emailValue
                    )

                    Log.i("SK9", "$userData lived:${emailValue} mutable:${tokenValue}")

                    if (check(userData)) {
                        authRepository.saveToken(userData)
                        emit(UserState.Authenticated)
                    } else {
                        emit(UserState.NotAuthenticated)
                    }
                }
            }
        }
    }

    private fun check(userData: UserData): Boolean {
        return userData.token != null && userData.token!!.isNotBlank()
    }

    private fun loadEmail() {
        Amplify.Auth.fetchUserAttributes(
            { attributes ->
                for (attribute in attributes) {
                    if (AuthUserAttributeKey.email().equals(attribute.key)) {
                        viewModelScope.launch {
                            _email.emit(attribute.value.toString())
                        }
                    }
                }
            },
            { Log.e("SK8", "Failed to fetch user attributes", it) }
        )
    }

    private fun loadToken() {
        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                when (session.identityId.type) {
                    AuthSessionResult.Type.SUCCESS -> {
                        Log.i("SK8", "IdentityId = ${session.identityId.value}")
                        viewModelScope.launch {
                            _token.emit(session.identityId.value.toString())
                        }
                    }
                    AuthSessionResult.Type.FAILURE ->
                        Log.w("SK8", "IdentityId not found", session.identityId.error)
                }
            },
            { Log.e("SK8", "Failed to fetch session", it) }
        )
    }
}

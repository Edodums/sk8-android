package unibo.it.loading.presentation

import android.util.Log
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.core.Amplify
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import unibo.it.domain.model.UserData
import unibo.it.domain.repository.AuthRepository
import unibo.it.loading_api.presentation.LoadingViewModel
import unibo.it.loading_api.presentation.UserState

@InternalCoroutinesApi
class LoadingViewModelImpl constructor(
    private val authRepository: AuthRepository
) : LoadingViewModel() {
    override val userState: Flow<UserState> = flow {
        var userData = authRepository.loadData().first()

        if (!check(userData)) {
            userData = UserData(
                token = getToken(),
                email = getEmail()
            )
        }

        when {
            !check(userData) -> {
                emit(UserState.NotAuthenticated)
            }
            else -> {
                authRepository.saveToken(userData)
                emit(UserState.Authenticated)
            }
        }
    }

    private fun check(userData: UserData): Boolean {
        return userData.token != null && userData.token!!.isNotBlank()
    }

    private fun getEmail(): String {
        var email = ""

        Amplify.Auth.fetchUserAttributes(
            { attributes ->
                for (attribute in attributes) {
                    if (AuthUserAttributeKey.email().equals(attribute.key)) {
                        email = attribute.value.toString()
                    }
                }
            },
            { Log.e("SK8", "Failed to fetch user attributes", it) }
        )

        return email
    }

    private fun getToken(): String {
        var token = ""

        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                when (session.identityId.type) {
                    AuthSessionResult.Type.SUCCESS -> {
                        Log.i("SK8", "IdentityId = ${session.identityId.value}")
                        token = session.identityId.toString()
                    }
                    AuthSessionResult.Type.FAILURE ->
                        Log.w("SK8", "IdentityId not found", session.identityId.error)
                }
            },
            { Log.e("SK8", "Failed to fetch session", it) }
        )

        return token
    }
}

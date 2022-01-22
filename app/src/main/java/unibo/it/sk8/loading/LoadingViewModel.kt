<<<<<<<< HEAD:features/loading/src/main/java/unibo/it/loading/presentation/LoadingViewModelImpl.kt
package unibo.it.loading.presentation
========
package unibo.it.sk8.loading
>>>>>>>> main:app/src/main/java/unibo/it/sk8/loading/LoadingViewModel.kt

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.amazonaws.mobile.auth.core.signin.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
<<<<<<<< HEAD:features/loading/src/main/java/unibo/it/loading/presentation/LoadingViewModelImpl.kt
import unibo.it.domain.model.UserData
import unibo.it.domain.repository.AuthRepository
import unibo.it.loading_api.presentation.LoadingViewModel
import unibo.it.loading_api.presentation.UserState
========
import unibo.it.sk8.data.UserData
import unibo.it.sk8.database.UserPreferences
>>>>>>>> main:app/src/main/java/unibo/it/sk8/loading/LoadingViewModel.kt

@InternalCoroutinesApi
class LoadingViewModelImpl constructor(
    authRepository: AuthRepository
) : LoadingViewModel() {
    private val _userState = MutableStateFlow<UserState>(UserState.Loading)

    init {
        viewModelScope.launch {
            authRepository.saveToken(
                UserData(
                    token = getToken(),
                    email = getEmail()
                )
            )

            authRepository.loadData().collect {
                it?.let {
                    _userState.value = UserState.Authenticated
                } ?: run {
                    _userState.value = UserState.NotAuthenticated
                }
            }
        }
    }

    override fun loadUserState(): Flow<UserState> = flow { _userState }

    private suspend fun getEmail(): String {
        var email = ""

        try {
            val attributes = Amplify.Auth.fetchUserAttributes()
            Log.i("AuthDemo", "User attributes = $attributes")

            for (attribute in attributes) {
                if (AuthUserAttributeKey.email().equals(attribute.key)) {
                    email = attribute.value.toString()
                }
            }
        } catch (error: AuthException) {
            Log.e("AuthDemo", "Failed to fetch user attributes", error)
        }

        return email
    }

    private suspend fun getToken(): String {
        var token = ""

        try {
            val session = Amplify.Auth.fetchAuthSession() as AWSCognitoAuthSession
            val id = session.identityId
            if (id.type == AuthSessionResult.Type.SUCCESS) {
                Log.i("AuthQuickStart", "IdentityId: ${id.value}")
                token = id.value.toString()
            } else if (id.type == AuthSessionResult.Type.FAILURE) {
                Log.i("AuthQuickStart", "IdentityId not present: ${id.error}")
            }
        } catch (error: AuthException) {
            Log.e("AuthQuickStart", "Failed to fetch session", error)
        }

        return token
    }
}

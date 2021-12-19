package unibo.it.sk8.loading

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazonaws.mobile.auth.core.signin.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.kotlin.core.Amplify
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import unibo.it.sk8.data.UserData
import unibo.it.sk8.database.UserPreferences

@HiltViewModel
class LoadingViewModel @Inject constructor(
    preferences: UserPreferences
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserState>(UserState.Loading)
    val uiState: StateFlow<UserState> = _uiState

    init {
        viewModelScope.launch {
            preferences.save(
                UserData(
                    token = getToken(),
                    email = getEmail()
                )
            )

            preferences.get().collect {
                it?.let {
                    _uiState.value = UserState.Authenticated
                } ?: run {
                    _uiState.value = UserState.NotAuthenticated
                }
            }
        }
    }

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

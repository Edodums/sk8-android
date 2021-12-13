package unibo.it.sk8.auth

import android.util.Log
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.InitializationStatus
import com.amplifyframework.hub.HubChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import unibo.it.sk8.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class AuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
) {
    fun isUserAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    private fun events() {
        Amplify.Hub.subscribe(HubChannel.AUTH) { event ->
            when (event.name) {
                InitializationStatus.SUCCEEDED.toString() ->
                    Log.i("AuthQuickstart", "Auth successfully initialized")
                InitializationStatus.FAILED.toString() ->
                    Log.i("AuthQuickstart", "Auth failed to succeed")
                else -> when (AuthChannelEventName.valueOf(event.name)) {
                    AuthChannelEventName.SIGNED_IN ->
                        Log.i("AuthQuickstart", "Auth just became signed in")
                    AuthChannelEventName.SIGNED_OUT ->
                        Log.i("AuthQuickstart", "Auth just became signed out")
                    AuthChannelEventName.SESSION_EXPIRED ->
                        Log.i("AuthQuickstart", "Auth session just expired")
                    else ->
                        Log.w("AuthQuickstart", "Unhandled Auth Event: ${event.name}")
                }
            }
        }
    }

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<Boolean>> {
/*        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()

        Amplify.Auth.signUp(email, password, options,
            { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
            { Log.e("AuthQuickStart", "Sign up failed", it) }
        )*/

        /*
            Amplify.Auth.signIn("username", "password",
                { result ->
                    if (result.isSignInComplete) {
                        Log.i("AuthQuickstart", "Sign in succeeded")
                    } else {
                        Log.i("AuthQuickstart", "Sign in not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to sign in", it) }
            )
         */
        TODO("Not yet implemented")
    }

    suspend fun signInWithCredentials(): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    suspend fun signOut(): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    fun getAuthState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    // Inizializza il provider di credenziali Amazon Cognito
    /*var credentialsProvider: CognitoCachingCredentialsProvider = CognitoCachingCredentialsProvider(
        ApplicationProvider.getApplicationContext(),
        "eu-west-1:485ff962-2f64-4868-aeea-5eae33a9c852",  // ID pool di identità
        Regions.EU_WEST_1 // Regione
    )*/


}

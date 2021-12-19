package unibo.it.sk8.auth

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.data.UserData
import unibo.it.sk8.database.UserPreferences

@Singleton
@ExperimentalCoroutinesApi
class AuthRepository @Inject constructor(
    private val dataSource: AuthDataSource,
    private val preferences: UserPreferences
) {

    suspend fun saveToken(token: String, email: String) {
        preferences.save(
            UserData(
                token = token,
                email = email
            )
        )
    }
}

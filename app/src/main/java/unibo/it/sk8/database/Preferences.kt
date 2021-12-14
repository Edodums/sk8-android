package unibo.it.sk8.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import unibo.it.sk8.data.UserData

class Preferences @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = TABLE_NAME)

    suspend fun saveUser(userData: UserData) {
        context.userDataStore.edit { preferences ->
            preferences[PreferenceKeys.token] = userData.token ?: ""
            preferences[PreferenceKeys.email] = userData.email ?: ""
        }
    }

    fun getUser(): Flow<UserData?> = context.userDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val authToken = preferences[PreferenceKeys.token]
            val authEmail = preferences[PreferenceKeys.email]

            if (authToken.isNullOrEmpty()) {
                return@map null
            }

            return@map UserData(
                token = authToken,
                email = authEmail
            )
        }

    private object PreferenceKeys {
        val token = stringPreferencesKey(TOKEN)
        val email = stringPreferencesKey(EMAIL)
    }

    companion object {
        private const val TABLE_NAME = "user"
        private const val TOKEN = "token"
        private const val EMAIL = "email"
    }
}

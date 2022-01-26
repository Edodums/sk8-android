package unibo.it.datastore.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import unibo.it.datastore.CommonDataStore
import unibo.it.datastore.mapper.UserMapper
import unibo.it.datastore.model.Data
import unibo.it.datastore.model.User
import unibo.it.repository.datasource.UserPreferenceDataSource
import unibo.it.repository.model.UserData

internal class UserPreferenceDataSourceImpl(
    override val context: Context,
    private val mapper: UserMapper
) : UserPreferenceDataSource, CommonDataStore(context = context) {
    private val token = stringPreferencesKey(TOKEN)
    private val email = stringPreferencesKey(EMAIL)

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = TABLE_NAME)

    override suspend fun save(data: Data) {
        context.dataStore.edit { userTable ->
            val user = data as User
            userTable[token] = user.token ?: ""
            userTable[email] = user.email ?: ""
        }
    }

    override fun get(): Flow<Data> =
        context.dataStore.data.map { preferences ->
            User(
                token = preferences[token],
                email = preferences[email]
            )
        }


    override suspend fun saveToken(userData: UserData) {
        save(mapper.toDataStore(userData))
    }

    override suspend fun loadData(): Flow<UserData> =
        get().map {
            val user = it as User
            mapper.toRepo(user)
        }

    companion object {
        private const val TABLE_NAME = "user"
        private const val TOKEN = "token"
        private const val EMAIL = "email"
    }
}
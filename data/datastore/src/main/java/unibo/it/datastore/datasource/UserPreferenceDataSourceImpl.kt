package unibo.it.datastore.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import unibo.it.datastore.CommonDataStore
import unibo.it.datastore.PreferenceKeys
import unibo.it.datastore.model.User
import unibo.it.repository.datasource.UserPreferenceDataSource

internal class UserPreferenceDataSourceImpl(
    override val context: Context
) : UserPreferenceDataSource, CommonDataStore(context = context) {
    override val preferenceKeys: PreferenceKeys = PreferenceKeys(
        keys = mutableMapOf(
            TOKEN to stringPreferencesKey(TOKEN),
            EMAIL to stringPreferencesKey(EMAIL)
        )
    )

    override val Context.store: DataStore<Preferences> by preferencesDataStore(name = TABLE_NAME)

    companion object {
        private const val TABLE_NAME = "user"
        private const val TOKEN = "token"
        private const val EMAIL = "email"
    }

    override suspend fun saveToken(token: String, email: String) {
        save(User(token, email))
    }
}
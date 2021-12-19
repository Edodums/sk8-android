package unibo.it.sk8.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPreferences @Inject constructor(
    @ApplicationContext override val context: Context
) : CommonPreferences(context = context) {
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
}

package unibo.it.sk8.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
<<<<<<< HEAD:data/datastore/src/main/java/unibo/it/datastore/datasource/UserPreferenceDataSourceImpl.kt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import unibo.it.datastore.CommonDataStore
import unibo.it.datastore.PreferenceKeys
import unibo.it.datastore.model.User
import unibo.it.repository.datasource.UserPreferenceDataSource
import unibo.it.repository.model.UserData
=======
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
>>>>>>> main:app/src/main/java/unibo/it/sk8/database/UserPreferences.kt

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
<<<<<<< HEAD:data/datastore/src/main/java/unibo/it/datastore/datasource/UserPreferenceDataSourceImpl.kt

    override suspend fun saveToken(userData: UserData) {
        save(
            User(
                token = userData.token,
                email = userData.email
            )
        )
    }

    override suspend fun loadData(): Flow<UserData?> = flow {
        get().collect { data -> data ?: run { null } }
    }
}
=======
}
>>>>>>> main:app/src/main/java/unibo/it/sk8/database/UserPreferences.kt

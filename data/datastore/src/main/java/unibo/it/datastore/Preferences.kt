package unibo.it.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import unibo.it.datastore.model.Data
import java.io.IOException

abstract class CommonDataStore constructor(
    open val context: Context
) {
    protected abstract val Context.store: DataStore<Preferences>
    protected abstract val preferenceKeys: PreferenceKeys

    suspend fun save(data: Data) {
        context.store.edit { preferences ->
            preferenceKeys.keys.map {
                val key = it.value as Preferences.Key<Any>
                val value = data.getValue(key = it.key)

                preferences[key] = value
            }
        }
    }

    fun get(): Flow<Data?> = context.store.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            Data(preferenceKeys.keys.map {
                val key = it.value as Preferences.Key<*>
                preferences[key]
            })
        }
}

class PreferenceKeys(val keys: MutableMap<String, Any>)

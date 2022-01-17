package unibo.it.sk8.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

abstract class CommonPreferences constructor(@ApplicationContext open val context: Context) {
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

open class Data(vararg data: Any) {
    fun getValue(key: String): Any {
        return this::class.members.map {
            if (it.name == key) {
                return@map it.call(this)
            }
            return@map null
        }.filterNotNull()[0]
    }
}

class PreferenceKeys(val keys: MutableMap<String, Any>)

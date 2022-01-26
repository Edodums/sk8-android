package unibo.it.datastore

import android.content.Context
import kotlinx.coroutines.flow.Flow
import unibo.it.datastore.model.Data

abstract class CommonDataStore constructor(
    open val context: Context
) {
    /**
     *
     */
    abstract suspend fun save(data: Data)

    /**
     *
     */
    abstract fun get(): Flow<Data>
}

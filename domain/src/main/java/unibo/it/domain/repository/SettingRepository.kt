package unibo.it.domain.repository

import kotlinx.coroutines.flow.Flow
import unibo.it.domain.model.Setting

interface SettingRepository {
    /**
     *
     */
    suspend fun saveChanges(changes: List<Setting>)

    /**
     *
     */
    suspend fun loadDefaults(): Flow<List<Setting>>
}
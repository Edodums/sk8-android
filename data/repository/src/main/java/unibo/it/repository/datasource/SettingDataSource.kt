package unibo.it.repository.datasource

import kotlinx.coroutines.flow.Flow
import unibo.it.repository.model.Setting

interface SettingDataSource {
    /**
     *
     */
    suspend fun insertSetting(setting: Setting)

    /**
     *
     */
    suspend fun insertSettings(settings: List<Setting>)

    /**
     *
     */
    fun loadSettings(): Flow<List<Setting>>

    /**
     *
     */
    suspend fun updateSetting(setting: Setting)

    /**
     *
     */
    suspend fun deleteSetting(escCommand: String)
}
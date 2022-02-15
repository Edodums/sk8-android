package unibo.it.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import unibo.it.local.model.Setting

@Dao
interface SettingDao {
    /**
     *
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertSetting(setting: Setting)

    /**
     * Inserts a new setting list.
     *
     * @param settings list of settings to be added
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertSettings(settings: List<Setting>)

    /**
     *
     */
    @Query("SELECT * FROM settings")
    fun loadSettings(): Flow<List<Setting>>

    /**
     *
     */
    @Update
    suspend fun updateSetting(setting: Setting)

    /**
     *
     */
    @Query("DELETE FROM settings WHERE esc_command = :escCommand")
    suspend fun deleteSetting(escCommand: String)
}

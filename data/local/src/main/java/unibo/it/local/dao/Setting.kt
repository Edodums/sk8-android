package unibo.it.local

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
)

@Dao
interface SettingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(setting: Setting)

    @Query("SELECT * FROM settings WHERE id = :id")
    fun load(id: String): Flow<Setting>

    @Query("DELETE FROM settings WHERE id = :id")
    fun deleteSetting(id: String)
}

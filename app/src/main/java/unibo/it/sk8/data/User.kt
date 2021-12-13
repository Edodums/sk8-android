package unibo.it.sk8.data


import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
)

@Immutable
data class UserData(
    val token: String,
    val email: String
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun load(id: String): Flow<User>

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUser(id: String)
}



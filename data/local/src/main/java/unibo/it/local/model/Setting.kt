package unibo.it.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey @ColumnInfo(name = "esc_command") var escCommand: String = "",
    @ColumnInfo(name = "esc_option") var escOption: String = ""
)
package unibo.it.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["device_UUID", "device_address"])
data class Device(
    @ColumnInfo(name = "device_UUID") var UUID: String = "",
    @ColumnInfo(name = "device_address") var address: String = "",
    @ColumnInfo(name = "device_name") var name: String = "",
    @ColumnInfo(name = "device_data") var data: List<String> = listOf(),
    @ColumnInfo(name = "device_created_at") var createdAt: Date = Date(),
    @ColumnInfo(name = "device_is_connected") var isConnected: Boolean = false
)

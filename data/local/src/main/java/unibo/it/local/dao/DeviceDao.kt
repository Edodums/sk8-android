package unibo.it.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import unibo.it.local.model.Device

@Dao
interface DeviceDao {

    /**
     * Gets a specific category by device name and device mac address .
     *
     * @param deviceAddress mac address
     * @param deviceName name that was given to the device
     */
    @Query("SELECT * FROM device WHERE device_address=:deviceAddress AND device_name=:deviceName")
    suspend fun findDeviceByPrimaryKeys(deviceAddress: String, deviceName: String): Device?

    /**
     * Inserts a new device.
     *
     * @param device device to be added
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertDevice(device: Device)

    /**
     * Updates a device.
     *
     * @param device device to be updated
     */
    @Update
    suspend fun updateDevice(device: Device)

    /**
     * Deletes a device.
     *
     * @param device device to be deleted
     */
    @Delete
    suspend fun deleteDevice(device: Device)

    /**
     * Purges the table.
     */
    @Query("DELETE FROM device")
    suspend fun purge()

    /**
     * Just gives the first row
     */
    @Query("SELECT * FROM device ORDER BY device_created_at DESC LIMIT 1")
    suspend fun getLastDevice(): Device?
}
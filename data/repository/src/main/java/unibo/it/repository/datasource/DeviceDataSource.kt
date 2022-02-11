package unibo.it.repository.datasource

import kotlinx.coroutines.flow.Flow
import unibo.it.repository.model.Device

interface DeviceDataSource {
    /**
     * Inserts a new device.
     *
     * @param device device to be added
     */
    suspend fun insertDevice(device: Device)

    /**
     * Updates the given device.
     *
     * @param device device to be updated
     */
    suspend fun updateDevice(device: Device)

    /**
     * Deletes a device.
     *
     * @param device task to be deleted
     */
    suspend fun deleteDevice(device: Device)

    /**
     * Purges the entire table.
     */
    suspend fun purge()

    /**
     * Gets a specific device by primary keys.
     *
     * @param deviceAddress mac address
     * @param deviceName name that was given to the device
     */
    suspend fun findDeviceByPrimaryKeys(
        deviceAddress: String,
        deviceName: String
    ): Device?

    /**
     * Always get the last inserted one
     */
    fun isLastDeviceConnected(): Flow<Boolean>
}

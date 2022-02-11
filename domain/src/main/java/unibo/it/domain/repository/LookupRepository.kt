package unibo.it.domain.repository

import unibo.it.domain.model.Device

interface LookupRepository {
    /**
     * Sends the device basic data to the datasource
     */
    suspend fun insertDevice(device: Device)
}
package unibo.it.domain.repository

import unibo.it.domain.model.Device


interface MenuRepository {
    /**
     *
     */
    suspend fun getLastDevice(): Device?

    /**
     *
     */
    suspend fun updateDevice(device: Device)
}
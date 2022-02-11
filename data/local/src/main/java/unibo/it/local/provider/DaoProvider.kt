package unibo.it.local.provider

import unibo.it.local.dao.DeviceDao

internal class DaoProvider(private val database: DatabaseProvider) {

    /**
     * Gets the [DeviceDao].
     *
     * @return the [DeviceDao]
     */
    fun getDeviceDao(): DeviceDao =
        database.getInstance().getDeviceDao()
}
package unibo.it.local.provider

import unibo.it.local.dao.DeviceDao
import unibo.it.local.dao.SettingDao

internal class DaoProvider(private val database: DatabaseProvider) {

    /**
     * Gets the [DeviceDao].
     *
     * @return the [DeviceDao]
     */
    fun getDeviceDao(): DeviceDao =
        database.getInstance().getDeviceDao()

    /*
     * Gets the [SettingDao].
     *
     * @return the [SettingDao]
     */
    fun getSettingDao(): SettingDao =
        database.getInstance().getSettingDao()

}
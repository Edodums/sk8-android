package unibo.it.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import unibo.it.local.converter.DateConverter
import unibo.it.local.converter.ListConverter
import unibo.it.local.dao.DeviceDao
import unibo.it.local.dao.SettingDao
import unibo.it.local.model.Device
import unibo.it.local.model.Setting


@Database(
    entities = [Device::class, Setting::class],
    version = 20,
    exportSchema = false
)
@TypeConverters(
    ListConverter::class,
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    /**
     *
     */
    abstract fun getSettingDao(): SettingDao

    /**
     *
     */
    abstract fun getDeviceDao(): DeviceDao
}

package unibo.it.sk8.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import unibo.it.local.dao.Setting
import unibo.it.local.dao.SettingDao


@Database(
    entities = [
        Setting::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSettingDao(): SettingDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {

            return Room
                .databaseBuilder(context, AppDatabase::class.java, "sk8-db")
                .addCallback(object : RoomDatabase.Callback() {

                })
                .build()
        }
    }
}

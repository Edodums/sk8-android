package unibo.it.local.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import unibo.it.local.AppDatabase

internal class DatabaseProvider(
    private val context: Context
) {
    @Volatile
    private var instance: AppDatabase? = null

    fun getInstance(): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "sk8-db")
             .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {

            })
            .build()
    }

    /*private fun onCreateDatabase() =
        object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                coroutineScope.launch {
                  insert here the default data to add},
                  remember to add coroutineScope and context in the constructor of the internal class
                }
            }
        }*/
}
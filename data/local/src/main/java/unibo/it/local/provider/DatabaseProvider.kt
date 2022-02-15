package unibo.it.local.provider

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import unibo.it.local.AppDatabase
import unibo.it.local.R
import unibo.it.local.model.Setting

internal class DatabaseProvider(
    private val context: Context,
    private val coroutineScope: CoroutineScope
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
            .addMigrations(MIGRATION_19_20)
            .build()
    }

    val MIGRATION_19_20 = object : Migration(19, 20) {
        override fun migrate(database: SupportSQLiteDatabase) {
            coroutineScope.launch {
                instance?.getSettingDao()?.insertSettings(getDefaultSettings())
            }
        }
    }

    private fun getDefaultSettings() =
        listOf(
            Setting(
                escCommand = context.getString(R.string.cutoff_voltage_command),
                escOption = context.getString(R.string.cutoff_voltage_option)
            ),
            Setting(
                escCommand = context.getString(R.string.throttle_limit_command),
                escOption = context.getString(R.string.throttle_limit_option)
            ),
            Setting(
                escCommand = context.getString(R.string.percent_braking_command),
                escOption = context.getString(R.string.percent_braking_option)
            ),
            Setting(
                escCommand = context.getString(R.string.initial_acc_command),
                escOption = context.getString(R.string.initial_acc_option)
            ),
            Setting(
                escCommand = context.getString(R.string.motor_timing_command),
                escOption = context.getString(R.string.motor_timing_option)
            ),
            Setting(
                escCommand = context.getString(R.string.motor_rotation_command),
                escOption = context.getString(R.string.motor_rotation_option)
            ),
            Setting(
                escCommand = context.getString(R.string.percent_drag_brake_command),
                escOption = context.getString(R.string.percent_drag_brake_option)
            ),
            Setting(
                escCommand = context.getString(R.string.neutral_range_command),
                escOption = context.getString(R.string.neutral_range_option)
            ),
            Setting(
                escCommand = context.getString(R.string.running_mode_command),
                escOption = context.getString(R.string.running_mode_option)
            ),
            Setting(
                escCommand = context.getString(R.string.percent_throttle_reverse_command),
                escOption = context.getString(R.string.percent_throttle_reverse_option)
            ),
            Setting(
                escCommand = context.getString(R.string.bec_output_command),
                escOption = context.getString(R.string.bec_output_option)
            )
        )
}
package unibo.it.local.converter

import androidx.room.TypeConverter
import java.util.Date

internal class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return when (value) {
            null -> null
            else -> Date(value)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
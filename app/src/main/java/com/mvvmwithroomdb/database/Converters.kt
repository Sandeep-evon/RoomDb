package com.mvvmwithroomdb.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun dateToLong(value: Date):Long{
        return value.time
    }
    @TypeConverter
    fun longToDate(value: Long): Date{
        return Date(value)
    }
}
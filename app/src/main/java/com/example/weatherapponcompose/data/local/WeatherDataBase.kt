package com.example.weatherapponcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Weather::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(WeatherDayConverter::class, WeatherHourConverter::class)
//abstract class WeatherDataBase : RoomDatabase() {
//    abstract fun weatherLocationDao(): WeatherDao
//}


@TypeConverters(Converter::class)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherLocationDao(): WeatherDao

    companion object {

        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getDatabase(
            context: Context
        ): WeatherDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "recipes"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
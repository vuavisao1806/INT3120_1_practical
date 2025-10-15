package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var Instace: ScheduleDatabase? = null

        fun getDatabse(context: Context): ScheduleDatabase {
            return Instace ?: synchronized(this) {
                Room.databaseBuilder(context, ScheduleDatabase::class.java, "bus_schedule_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                    .also { Instace = it }
            }
        }
    }
}


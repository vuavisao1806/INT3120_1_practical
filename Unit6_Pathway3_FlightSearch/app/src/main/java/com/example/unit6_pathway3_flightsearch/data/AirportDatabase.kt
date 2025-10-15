package com.example.unit6_pathway3_flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class], version = 1, exportSchema = false)
abstract class AirportDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao

    companion object {
        @Volatile
        private var Instace: AirportDatabase? = null

        fun getDatabse(context: Context): AirportDatabase {
            return Instace ?: synchronized(this) {
                Room.databaseBuilder(context, AirportDatabase::class.java, "airport_database")
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also { Instace = it }
            }
        }
    }
}


package com.example.unit6_pathway3_flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Flight::class], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var Instace: FlightDatabase? = null

        fun getDatabse(context: Context): FlightDatabase {
            return Instace ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDatabase::class.java, "favorite_database")
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also { Instace = it }
            }
        }
    }
}
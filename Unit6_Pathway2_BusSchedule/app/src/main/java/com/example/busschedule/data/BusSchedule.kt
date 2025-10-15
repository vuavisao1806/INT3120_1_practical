package com.example.busschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule")
data class BusSchedule(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("stop_name")
    val stopName: String,
    @ColumnInfo("arrival_time")
    val arrivalTimeInMillis: Int
)

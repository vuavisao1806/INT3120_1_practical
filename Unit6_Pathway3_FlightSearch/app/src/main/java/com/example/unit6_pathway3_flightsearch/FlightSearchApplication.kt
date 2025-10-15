package com.example.unit6_pathway3_flightsearch

import android.app.Application
import com.example.unit6_pathway3_flightsearch.data.AppContainer
import com.example.unit6_pathway3_flightsearch.data.AppDataContainer

class FlightSearchApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

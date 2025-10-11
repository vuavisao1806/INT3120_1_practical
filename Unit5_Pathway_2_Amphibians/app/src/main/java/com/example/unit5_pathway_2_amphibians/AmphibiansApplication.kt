package com.example.unit5_pathway_2_amphibians

import android.app.Application
import com.example.unit5_pathway_2_amphibians.data.AppContainer
import com.example.unit5_pathway_2_amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
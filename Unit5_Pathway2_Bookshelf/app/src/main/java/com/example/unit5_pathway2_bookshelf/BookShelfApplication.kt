package com.example.unit5_pathway2_bookshelf

import android.app.Application
import com.example.unit5_pathway2_bookshelf.data.AppContainer
import com.example.unit5_pathway2_bookshelf.data.DefaultAppContainer

class BookShelfApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
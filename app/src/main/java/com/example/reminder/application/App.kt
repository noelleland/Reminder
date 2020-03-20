package com.example.reminder.application

import android.app.Application
import android.content.Intent

class App : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
        lateinit var globalSharedPreferences: GlobalSharedPreferences
    }

    override fun onCreate() {
        globalSharedPreferences = GlobalSharedPreferences(applicationContext)
        //startService(Intent(applicationContext, ScreenService::class.java))
        super.onCreate()
    }
}
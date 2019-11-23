package com.example.remainder

import android.app.Application
import android.os.PowerManager

class App : Application() {

    companion object {
        lateinit var globalSharedPreferences: GlobalSharedPreferences
    }

    override fun onCreate() {
        globalSharedPreferences = GlobalSharedPreferences(applicationContext)
        super.onCreate()
    }
}
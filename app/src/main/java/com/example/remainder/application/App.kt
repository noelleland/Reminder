package com.example.remainder.application

import android.app.Application

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
        super.onCreate()
    }
}
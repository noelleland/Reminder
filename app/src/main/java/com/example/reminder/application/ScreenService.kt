package com.example.reminder.application

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ScreenService : Service() {

    private var screenReceiver: ScreenReceiver? = null

    override fun onCreate() {
        super.onCreate()
        screenReceiver = ScreenReceiver()
        val filter = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {
            if (intent.action == null && screenReceiver == null) {
                screenReceiver = ScreenReceiver()
                val filter = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
                filter.addAction(Intent.ACTION_SCREEN_OFF)
                registerReceiver(screenReceiver, filter)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (screenReceiver != null) {
            unregisterReceiver(screenReceiver)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}

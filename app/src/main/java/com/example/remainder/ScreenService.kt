package com.example.remainder

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ScreenService : Service() {

    private var screenReceiver: ScreenReceiver? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        screenReceiver = ScreenReceiver()
        var intentFilter: IntentFilter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {
            if (intent.action == null && screenReceiver == null) {
                screenReceiver = ScreenReceiver()
                var intentFilter: IntentFilter = IntentFilter(Intent.ACTION_SCREEN_OFF)
                registerReceiver(screenReceiver, intentFilter)
            }
        }

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        if (screenReceiver != null) {
            unregisterReceiver(screenReceiver)
        }
    }

}

package com.example.remainder.application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager

class ScreenReceiver : BroadcastReceiver() {

    var wakeLock: PowerManager.WakeLock? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (wakeLock != null) {
            return
        }

        var powerManager: PowerManager = context?.getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                        or PowerManager.ACQUIRE_CAUSES_WAKEUP
                        or PowerManager.ON_AFTER_RELEASE, "hi:")
        wakeLock?.acquire()

        if (wakeLock != null){
            wakeLock?.release()
            wakeLock = null
        }

    }
}
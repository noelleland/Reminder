package com.example.reminder.application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.reminder.activity.MainActivity

class ScreenReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == Intent.ACTION_SCREEN_OFF
                || intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val newIntent = Intent(context, MainActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    or Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            newIntent.putExtra("turn_on", true)
            context!!.startActivity(newIntent)
        }
    }
}
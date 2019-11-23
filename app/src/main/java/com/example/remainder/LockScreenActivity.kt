package com.example.remainder

import android.app.KeyguardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as? KeyguardManager?
        keyguardManager?.requestDismissKeyguard(this, null)
        setContentView(R.layout.activity_lock_screen)
    }

}

package com.example.reminder.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reminder.application.App
import com.example.reminder.R
import com.example.reminder.application.ScreenService
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, null)

        view.switch_lockscreen.isChecked = App.globalSharedPreferences.lockScreen
        view.switch_lockscreen.setOnCheckedChangeListener { buttonView, isChecked ->
            val intent: Intent =  Intent(context, ScreenService::class.java)
            if (isChecked) {
                context?.startService(intent)
            }
            else {
                context?.stopService(intent)
            }
            App.globalSharedPreferences.lockScreen = isChecked
        }

        view.downloadDefaultButton.setOnClickListener {
            App.globalSharedPreferences.CONNECTION_CONTROLLER.downLoadDefault()
        }

        view.syncWithServerButton.setOnClickListener {
            App.globalSharedPreferences.CONNECTION_CONTROLLER.syncWithServer()
        }

        return view
    }
}
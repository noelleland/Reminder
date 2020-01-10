package com.example.remainder.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.application.AppDatabase
import com.example.remainder.fragment.CalendarFragment
import com.example.remainder.fragment.SettingsFragment
import com.example.remainder.fragment.TimelineFragment
import com.example.remainder.fragment.WriteMemoFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_menu.*

class MainActivity : AppCompatActivity() {

    var userId: String = "";
    private var appDB: AppDatabase? = null
    private var appData: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDB = AppDatabase.getInstance(this)

        getSharedPreferences("appData", Context.MODE_PRIVATE)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        // Initialize the action bar drawer toggle instance
        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            0,
            0
        ) {
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)

                button_menu_calendar.setOnClickListener {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, CalendarFragment())
                        .commit()
                    drawer_layout.closeDrawer(Gravity.LEFT)
                }

                button_menu_writememo.setOnClickListener {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, WriteMemoFragment())
                        .commit()
                    drawer_layout.closeDrawer(Gravity.LEFT)
                }

                button_menu_timeline.setOnClickListener {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, TimelineFragment())
                        .commit()
                    drawer_layout.closeDrawer(Gravity.LEFT)
                }

                button_menu_settings.setOnClickListener {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, SettingsFragment())
                        .commit()
                    drawer_layout.closeDrawer(Gravity.LEFT)
                }

            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if (supportFragmentManager.findFragmentById(R.id.fragment) !is CalendarFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, CalendarFragment())
                .commit()
        }
        else {
            super.onBackPressed()
        }
    }

    fun getAppData(): SharedPreferences? {
        return appData
    }

}

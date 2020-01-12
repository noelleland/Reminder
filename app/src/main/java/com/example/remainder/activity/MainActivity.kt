package com.example.remainder.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.remainder.R
import com.example.remainder.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                button_menu_calendar.setOnClickListener {
                    fragmentChange(CalendarFragment())
                }
                button_menu_writememo.setOnClickListener {
                    fragmentChange(WriteMemoFragment())
                }
                button_menu_timeline.setOnClickListener {
                    fragmentChange(TimetableFragment())
                }
                button_menu_settings.setOnClickListener {
                    fragmentChange(SettingsFragment())
                }
                memo_mamage_button.setOnClickListener {
                    fragmentChange(ManageMemoFragment())
                }
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun fragmentChange(fragment: Fragment) {
        memo_mamage_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit()
            drawer_layout.closeDrawer(Gravity.LEFT)
        }
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

}

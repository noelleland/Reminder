package com.example.reminder.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.reminder.R
import com.example.reminder.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, WriteMemoFragment())
                .commit()
        drawer_layout.closeDrawer(Gravity.LEFT)

        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                bindButtonAndFragment(button_menu_calendar, CalendarFragment())
                bindButtonAndFragment(button_menu_writememo, WriteMemoFragment())
                bindButtonAndFragment(button_menu_timeline, TimetableFragment())
                bindButtonAndFragment(button_menu_settings, SettingsFragment())
                bindButtonAndFragment(memo_mamage_button, ManageMemoFragment())
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun bindButtonAndFragment(button: Button, fragment: Fragment) {
        button.setOnClickListener {
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
        else if (supportFragmentManager.findFragmentById(R.id.fragment) !is WriteMemoFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, WriteMemoFragment())
                .commit()
        }
        else {
            super.onBackPressed()
        }
    }

}

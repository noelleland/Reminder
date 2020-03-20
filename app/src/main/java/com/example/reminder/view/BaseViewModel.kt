package com.example.reminder.view

import androidx.lifecycle.ViewModel
import com.example.reminder.application.App
import com.example.reminder.application.AppRepository

abstract class BaseViewModel : ViewModel() {
    protected val appRepository: AppRepository = App.globalSharedPreferences.REPOSITORY
}
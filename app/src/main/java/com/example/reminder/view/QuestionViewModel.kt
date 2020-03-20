package com.example.reminder.view

import androidx.lifecycle.MutableLiveData

class QuestionViewModel : BaseViewModel() {

    val selectedQuestionIndex = MutableLiveData<Int>()

    fun getQuestionList() = appRepository.getAllQuestion()

}
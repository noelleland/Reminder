package com.example.reminder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.QuestionEntity

@Dao
interface QuestionDao : BaseDao<QuestionEntity> {

    @Query("SELECT * FROM ${QuestionEntity.TABLE_NAME}")
    override fun getAll(): LiveData<List<QuestionEntity>>

    @Query("SELECT * FROM ${QuestionEntity.TABLE_NAME} WHERE ${QuestionEntity.TABLE_NAME}.idx = :idx")
    fun getQuestion(idx: Int): LiveData<QuestionEntity>

    @Query("SELECT ${QuestionEntity.TABLE_NAME}.content FROM ${QuestionEntity.TABLE_NAME} WHERE ${QuestionEntity.TABLE_NAME}.idx = :index")
    fun getQuestionString(index: Int): LiveData<String>

    @Query("SELECT ${QuestionEntity.TABLE_NAME}.idx FROM ${QuestionEntity.TABLE_NAME} WHERE ${QuestionEntity.TABLE_NAME}.content = :content")
    fun getQuestionIndexByContent(content: String): LiveData<Int>

}
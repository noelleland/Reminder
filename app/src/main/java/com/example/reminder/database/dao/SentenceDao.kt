package com.example.reminder.database.dao;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Query;
import com.example.reminder.database.entity.SentenceEntity

@Dao
interface SentenceDao : BaseDao<SentenceEntity> {

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME}")
    override fun getAll(): LiveData<List<SentenceEntity>>

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME} WHERE ${SentenceEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): LiveData<SentenceEntity>
}
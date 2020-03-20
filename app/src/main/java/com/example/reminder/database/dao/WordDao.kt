package com.example.reminder.database.dao;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.WordEntity
import io.reactivex.Single

@Dao
interface WordDao : BaseDao<WordEntity> {

    @Query("SELECT * FROM ${WordEntity.TABLE_NAME}")
    override fun getAll(): LiveData<List<WordEntity>>

    @Query("SELECT * FROM ${WordEntity.TABLE_NAME} WHERE ${WordEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): LiveData<WordEntity>
}

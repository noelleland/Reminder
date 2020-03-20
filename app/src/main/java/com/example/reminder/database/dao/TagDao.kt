package com.example.reminder.database.dao;

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.TagEntity

@Dao
interface TagDao : BaseDao<TagEntity> {

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME}")
    override fun getAll(): LiveData<List<TagEntity>>

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME} WHERE ${TagEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): LiveData<TagEntity>
}

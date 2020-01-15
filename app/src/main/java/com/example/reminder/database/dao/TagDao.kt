package com.example.reminder.database.dao;

import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.TagEntity
import io.reactivex.Single

@Dao
interface TagDao : BaseDao<TagEntity> {

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME} WHERE ${TagEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): TagEntity

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME}")
    override fun getAll(): Single<List<TagEntity>>


}

package com.example.remainder.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.DiaryEntity

@Dao
interface DiaryDao : BaseDao<DiaryEntity> {

    @Query("SELECT * FROM ${DiaryEntity.TABLE_NAME}")
    fun getMemo(): List<DiaryEntity>

    @Query("SELECT * FROM ${DiaryEntity.TABLE_NAME} WHERE d_id = :id")
    fun getMemoById(id: Int): DiaryEntity

    @Query("DELETE FROM ${DiaryEntity.TABLE_NAME}")
    fun deleteAllUsers()
}
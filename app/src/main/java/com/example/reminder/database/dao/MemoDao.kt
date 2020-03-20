package com.example.reminder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.MemoEntity

@Dao
interface MemoDao : BaseDao<MemoEntity> {

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} ORDER BY ${MemoEntity.TABLE_NAME}.writeTime")
    override fun getAll(): LiveData<List<MemoEntity>>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE ${MemoEntity.TABLE_NAME}.idx = :idx")
    fun getMemo(idx: Int): LiveData<MemoEntity>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE ${MemoEntity.TABLE_NAME}.writeTime LIKE :dateString ORDER BY ${MemoEntity.TABLE_NAME}.writeTime")
    fun getMemoByDate(dateString : String): LiveData<List<MemoEntity>>

}
package com.example.remainder.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.MemoEntity

@Dao
interface MemoDao : BaseDao<MemoEntity> {

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME}")
    fun getMemo(): List<MemoEntity>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE m_id = :id")
    fun getMemoById(id: Int): MemoEntity

    @Query("DELETE FROM ${MemoEntity.TABLE_NAME}")
    fun deleteAllMemo()

}
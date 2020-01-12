package com.example.remainder.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.MemoEntity
import io.reactivex.Single

@Dao
interface MemoDao : BaseDao<MemoEntity> {

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE ${MemoEntity.TABLE_NAME}.idx = :idx")
    fun getMemo(idx: Int): Single<MemoEntity>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} ORDER BY ${MemoEntity.TABLE_NAME}.writeTime")
    override fun getAll(): Single<List<MemoEntity>>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE ${MemoEntity.TABLE_NAME}.writeTime LIKE :dateString ORDER BY ${MemoEntity.TABLE_NAME}.writeTime")
    fun getMemo(dateString : String): Single<List<MemoEntity>>

}
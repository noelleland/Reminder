package com.example.remainder.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.MemoEntity
import io.reactivex.Single

@Dao
interface MemoDao : BaseDao<MemoEntity> {

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME} WHERE 'idx' = :idx")
    fun getMemo(idx: Int): Single<MemoEntity>

    @Query("SELECT * FROM ${MemoEntity.TABLE_NAME}")
    override fun getAll(): Single<List<MemoEntity>>

}
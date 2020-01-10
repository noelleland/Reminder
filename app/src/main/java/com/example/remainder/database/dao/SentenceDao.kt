package com.example.remainder.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.remainder.database.entity.SentenceEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface SentenceDao : BaseDao<SentenceEntity> {

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME} WHERE 'idx' = :idx")
    fun selectById(idx: Int): Maybe<SentenceEntity>

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME}")
    override fun getAll(): Single<List<SentenceEntity>>

}
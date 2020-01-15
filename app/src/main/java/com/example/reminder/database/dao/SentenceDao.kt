package com.example.reminder.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.reminder.database.entity.SentenceEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface SentenceDao : BaseDao<SentenceEntity> {

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME} WHERE ${SentenceEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): Maybe<SentenceEntity>

    @Query("SELECT * FROM ${SentenceEntity.TABLE_NAME}")
    override fun getAll(): Single<List<SentenceEntity>>

}
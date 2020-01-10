package com.example.remainder.database.dao;

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.TagEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface TagDao : BaseDao<TagEntity> {

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME} WHERE 'idx' = :idx")
    fun selectById(idx: Int): TagEntity

    @Query("SELECT * FROM ${TagEntity.TABLE_NAME}")
    override fun getAll(): Single<List<TagEntity>>


}

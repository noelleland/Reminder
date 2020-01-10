package com.example.remainder.database.dao;

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.UserDataEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDataDao : BaseDao<UserDataEntity> {

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME} WHERE 'idx' = :idx")
    fun selectById(idx: Int): UserDataEntity

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME}")
    override fun getAll(): Single<List<UserDataEntity>>

}

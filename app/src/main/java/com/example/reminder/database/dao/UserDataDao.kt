package com.example.reminder.database.dao;

import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.UserDataEntity
import io.reactivex.Single

@Dao
interface UserDataDao : BaseDao<UserDataEntity> {

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME} WHERE ${UserDataEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): UserDataEntity

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME}")
    override fun getAll(): Single<List<UserDataEntity>>

}

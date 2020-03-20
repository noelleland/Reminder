package com.example.reminder.database.dao;

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.reminder.database.entity.UserDataEntity

@Dao
interface UserDataDao : BaseDao<UserDataEntity> {

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME}")
    override fun getAll(): LiveData<List<UserDataEntity>>

    @Query("SELECT * FROM ${UserDataEntity.TABLE_NAME} WHERE ${UserDataEntity.TABLE_NAME}.idx = :idx")
    fun selectById(idx: Int): LiveData<UserDataEntity>
}

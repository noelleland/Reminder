package com.example.reminder.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import com.example.reminder.database.entity.BaseEntity

interface BaseDao<ET : BaseEntity> {
    fun getAll(): LiveData<List<ET>>
    @Insert(onConflict = REPLACE)
    fun insert(entity: ET)
    @Insert(onConflict = REPLACE)
    fun insert(entity: Array<out ET>)
    @Update(onConflict = REPLACE)
    fun update(entity: ET)
    @Delete
    fun delete(entity: ET)
}
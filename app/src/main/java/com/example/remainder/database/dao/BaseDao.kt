package com.example.remainder.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)
    @Insert
    fun insert(vararg obj: T)
    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj: T)
    @Delete
    fun delete(obj: T)

}
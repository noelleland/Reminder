package com.example.reminder.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import com.example.reminder.database.entity.BaseEntity
import io.reactivex.Single

interface BaseDao<T : BaseEntity> {

    fun getAll(): Single<List<T>>
    @Insert(onConflict = REPLACE)
    fun insert(obj: T)
    @Insert(onConflict = REPLACE)
    fun insert(vararg obj: T)
    @Update(onConflict = REPLACE)
    fun update(obj: T)
    @Delete
    fun delete(obj: T)

}
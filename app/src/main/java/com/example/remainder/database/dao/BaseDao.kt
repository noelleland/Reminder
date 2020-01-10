package com.example.remainder.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.remainder.database.entity.BaseEntity
import com.example.remainder.database.entity.MemoEntity
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
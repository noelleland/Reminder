package com.example.remainder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.remainder.database.dao.MemoDao
import com.example.remainder.database.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDB : RoomDatabase() {
    abstract fun MemoDao(): MemoDao

    companion object {
        private var INSTANCE: MemoDB? = null

        fun getInstance(context: Context): MemoDB? {
            if(INSTANCE == null) {
                synchronized(MemoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MemoDB::class.java, MemoEntity.TABLE_NAME + ".db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
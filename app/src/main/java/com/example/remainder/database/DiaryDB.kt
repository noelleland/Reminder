package com.example.remainder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.remainder.database.dao.DiaryDao
import com.example.remainder.database.entity.DiaryEntity

@Database(entities = [DiaryEntity::class], version = 1)
abstract class DiaryDB : RoomDatabase() {
    abstract fun DiaryDao(): DiaryDao

    companion object {
        private var INSTANCE: DiaryDB? = null

        fun getInstance(context: Context): DiaryDB? {
            if(INSTANCE == null) {
                synchronized(DiaryDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                DiaryDB::class.java, DiaryEntity.TABLE_NAME + ".db")
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
package com.example.reminder.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reminder.database.dao.*
import com.example.reminder.database.entity.*

@Database(entities = [MemoEntity::class, QuestionEntity::class, SentenceEntity::class, TagEntity::class
, UserDataEntity::class, WordEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao
    abstract fun questionDao() : QuestionDao
    abstract fun sentenceDao() : SentenceDao
    abstract fun tagDao() : TagDao
    abstract fun userDataDao() : UserDataDao
    abstract fun wordDao() : WordDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "app_database.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }

            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
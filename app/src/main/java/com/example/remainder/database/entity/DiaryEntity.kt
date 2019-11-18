package com.example.remainder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DiaryEntity.TABLE_NAME)
data class DiaryEntity(@PrimaryKey val d_id: Long,
                        @ColumnInfo(name = "d_date") val d_date: String,
                        @ColumnInfo(name = "d_content") val d_content: String) {
    companion object {
        const val TABLE_NAME = "Diary"
    }
}



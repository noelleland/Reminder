package com.example.remainder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MemoEntity.TABLE_NAME)
data class MemoEntity(@PrimaryKey val m_id: Long,
                      @ColumnInfo(name = "m_startTime") val m_startTime: String,
                      @ColumnInfo(name = "m_endTime") val m_endTime: String,
                      @ColumnInfo(name = "m_content") val m_content: String) {
    companion object {
        const val TABLE_NAME = "Memo"
    }
}
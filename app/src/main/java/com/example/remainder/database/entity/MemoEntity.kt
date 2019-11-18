package com.example.remainder.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MemoEntity.TABLE_NAME)
data class MemoEntity(@PrimaryKey val m_id: Int, val m_date: String, val m_content: String) {
    companion object {
        const val TABLE_NAME = "Memo"
    }
}
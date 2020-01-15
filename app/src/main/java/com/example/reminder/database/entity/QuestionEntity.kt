package com.example.reminder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.reminder.database.entity.QuestionEntity.Companion.TABLE_NAME
import org.json.JSONObject

@Entity(tableName = TABLE_NAME, indices = [Index(value=["content"], unique = true)])
data class QuestionEntity(@PrimaryKey(autoGenerate = true) val idx: Int?,
                          @ColumnInfo(name = "content") var content: String,
                          @ColumnInfo(name = "tag_idx") var tag_idx: Int) : BaseEntity {
    companion object {
        const val TABLE_NAME = "question"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("content", content)
        entityObject.put("tag_idx", tag_idx)
        return entityObject
    }

}
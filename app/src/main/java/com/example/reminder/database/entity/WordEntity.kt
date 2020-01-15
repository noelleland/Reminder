package com.example.reminder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.database.entity.WordEntity.Companion.TABLE_NAME
import org.json.JSONObject

@Entity(tableName = TABLE_NAME)
data class WordEntity(@PrimaryKey(autoGenerate = true) val idx: Int,
                          @ColumnInfo(name = "content") var content: String,
                          @ColumnInfo(name = "tag_idx") var tag_idx: Int) : BaseEntity {

    companion object {
        const val TABLE_NAME = "word"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("content", content)
        entityObject.put("tag_idx", tag_idx)
        return entityObject
    }
}
package com.example.reminder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.database.entity.TagEntity.Companion.TABLE_NAME
import org.json.JSONObject

@Entity(tableName = TABLE_NAME)
data class TagEntity(@PrimaryKey(autoGenerate = true) val idx: Int?,
                     @ColumnInfo(name = "tagType") var tagType: String,
                     @ColumnInfo(name = "content") var content: String) : BaseEntity {

    companion object {
        const val TABLE_NAME = "tag"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("tagType", tagType)
        entityObject.put("content", content)
        return entityObject
    }

}
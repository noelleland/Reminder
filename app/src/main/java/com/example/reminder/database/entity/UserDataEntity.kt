package com.example.reminder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.database.entity.UserDataEntity.Companion.TABLE_NAME
import org.json.JSONObject

@Entity(tableName = TABLE_NAME)
data class UserDataEntity(@PrimaryKey(autoGenerate = false) val idx: Int,
                          @ColumnInfo(name = "userId") var userId: String,
                          @ColumnInfo(name = "content_idx") var content_idx: Int,
                          @ColumnInfo(name = "tag_idx") var tag_idx: Int,
                          @ColumnInfo(name = "content") var content: String) : BaseEntity {

    companion object {
        const val TABLE_NAME = "user_data"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("userId", userId)
        entityObject.put("content_idx", content_idx)
        entityObject.put("tag_idx", tag_idx)
        entityObject.put("content", content)
        return entityObject
    }
}
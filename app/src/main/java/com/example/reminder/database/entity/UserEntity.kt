package com.example.reminder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.database.entity.UserEntity.Companion.TABLE_NAME
import org.json.JSONObject

@Entity(tableName = TABLE_NAME)
data class UserEntity(@PrimaryKey(autoGenerate = false) val userId: String,
                      @ColumnInfo(name = "passwd") var passwd: String) : BaseEntity {

    companion object {
        const val TABLE_NAME = "user_account"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("userId", userId)
        entityObject.put("passwd", passwd)
        return entityObject
    }
}
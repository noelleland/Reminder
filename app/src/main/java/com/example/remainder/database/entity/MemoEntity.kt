package com.example.remainder.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.remainder.database.entity.MemoEntity.Companion.TABLE_NAME
import org.json.JSONObject
import java.sql.Time

@Entity(tableName =  TABLE_NAME)
data class MemoEntity(@PrimaryKey(autoGenerate = true) val idx: Int?,
                      @ColumnInfo(name = "userId") var userId: String,
                      @ColumnInfo(name = "writeTime") var writeTime: Time,
                      @ColumnInfo(name = "question_idx") var question_idx: Int,
                      @ColumnInfo(name = "answer") var answer: String?) : BaseEntity {

    companion object {
        const val TABLE_NAME = "memo"
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("userId", userId)
        entityObject.put("writeTime", writeTime)
        entityObject.put("question_idx", question_idx)
        entityObject.put("answer", answer)
        return entityObject
    }

}
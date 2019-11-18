package com.example.remainder.database

import android.provider.BaseColumns

object DBContract {
    class MemoEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "memo"
            val COLUMN_MEMO_ID = "m_id"
            val COLUMN_MEMO_DATE = "m_date"
            val COLUMN_MEMO_CONTENT = "m_content"
        }
    }

    class DiaryEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "diary"
            val COLUMN_DIARY_ID = "d_id"
            val COLUMN_DIARY_DATE = "d_date"
            val COLUMN_DIARY_CONTENT = "d_content"
        }
    }
}

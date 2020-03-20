package com.example.reminder.application

import androidx.lifecycle.LiveData
import com.example.reminder.database.dao.*
import com.example.reminder.database.entity.*
import org.json.JSONArray
import org.json.JSONObject

class AppRepository {

    private val memoDao: MemoDao
    private val questionDao: QuestionDao
    private val sentenceDao: SentenceDao
    private val wordDao: WordDao
    private val userDataDao: UserDataDao
    private val tagDao: TagDao

    init {
        val database = AppDatabase.getInstance(App.INSTANCE)
        memoDao = database.memoDao()
        questionDao = database.questionDao()
        sentenceDao = database.sentenceDao()
        wordDao = database.wordDao()
        userDataDao = database.userDataDao()
        tagDao = database.tagDao()
    }

    companion object {
        private var INSTANCE: AppRepository? = null
        fun getInstance(): AppRepository {
            if (INSTANCE == null) {
                INSTANCE = AppRepository()
            }
            return INSTANCE!!
        }
    }

    fun getAllMemo(): LiveData<List<MemoEntity>> {
        return memoDao.getAll()
    }

    fun getAllQuestion(): LiveData<List<QuestionEntity>> {
        return questionDao.getAll()
    }

    fun getAllMemoByDateString(dateString: String): LiveData<List<MemoEntity>> {
        return memoDao.getMemoByDate("${dateString}%")
    }

    fun getQuestionContent(index: Int): LiveData<String>? {
        return questionDao.getQuestionString(index)
    }

    fun getQuestionIndexByContent(content: String): LiveData<Int>? {
        return questionDao.getQuestionIndexByContent(content)
    }

    fun insertMemo(memoEntity: MemoEntity) {
        this.memoDao.insert(memoEntity)
    }

    fun deleteMemo(memoEntity: MemoEntity) {
        this.memoDao.delete(memoEntity)
    }

    fun setDefault(jsonString: String) {
        val tablesObject = JSONObject(jsonString)
        val tableArray: JSONArray = tablesObject.getJSONArray("tables")
        for (index in 0 until tableArray.length()) {
            val table = tableArray.getJSONObject(index)
            val tableName = table.getString("table_name")
            val tableData = table.getJSONArray("data")
            for (index2 in 0 until tableData.length()) {
                when (tableName) {
                    "tag" -> {
                        val tagObject = tableData.getJSONObject(index2)
                        val tagType = tagObject.getString("tagType")
                        val content = tagObject.getString("content")
                        this.tagDao.insert(TagEntity(null, tagType, content))
                    }
                    "question" -> {
                        val tagObject = tableData.getJSONObject(index2)
                        val content = tagObject.getString("content")
                        val tag_idx = tagObject.getInt("tag_idx")
                        this.questionDao.insert(QuestionEntity(null, content, tag_idx))
                    }
                }
            }
        }
    }
}
package com.example.remainder.application

import android.os.AsyncTask
import com.example.remainder.database.TaskType
import com.example.remainder.database.dao.*
import com.example.remainder.database.entity.*
import org.json.JSONArray
import org.json.JSONObject

class DatabaseController {

    private var memoDao: MemoDao
    private var questionDao: QuestionDao
    private var sentenceDao: SentenceDao
    private var wordDao: WordDao
    private var userDataDao: UserDataDao
    private var tagDao: TagDao

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
        private var INSTANCE: DatabaseController? = null
        fun getInstance(): DatabaseController {
            if (INSTANCE == null) {
                INSTANCE = DatabaseController()
            }
            return INSTANCE!!
        }
    }

    fun getAll(tableName: String): List<BaseEntity> {
        var returnList: List<BaseEntity>? = null
        when (tableName) {
            MemoEntity.TABLE_NAME -> returnList = memoDao.getAll().blockingGet()
            QuestionEntity.TABLE_NAME -> returnList = questionDao.getAll().blockingGet()
            SentenceEntity.TABLE_NAME -> returnList = sentenceDao.getAll().blockingGet()
            TagEntity.TABLE_NAME -> returnList = tagDao.getAll().blockingGet()
            UserDataEntity.TABLE_NAME -> returnList = userDataDao.getAll().blockingGet()
            WordEntity.TABLE_NAME -> returnList = wordDao.getAll().blockingGet()
        }
        return returnList!!
    }

    private fun entityControl(entity: BaseEntity, taskType: TaskType) {
        when (entity) {
            is MemoEntity -> DMLAsyncTask(memoDao, taskType).execute(entity)
            is QuestionEntity -> DMLAsyncTask(questionDao, taskType).execute(entity)
            is SentenceEntity -> DMLAsyncTask(sentenceDao, taskType).execute(entity)
            is WordEntity -> DMLAsyncTask(wordDao, taskType).execute(entity)
            is UserDataEntity -> DMLAsyncTask(userDataDao, taskType).execute(entity)
            is TagEntity -> DMLAsyncTask(tagDao, taskType).execute(entity)
        }
    }

    fun getQuestionIndexByContent(content: String): Int {
        return questionDao.getQuestionIndexByContent(content).blockingGet()
    }

    fun memoInsert(newMemo: MemoEntity) {
        this.entityControl(newMemo, TaskType.INSERT)
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
                        entityControl(TagEntity(null, tagType, content), TaskType.INSERT)
                    }
                    "question" -> {
                        val tagObject = tableData.getJSONObject(index2)
                        val content = tagObject.getString("content")
                        val tag_idx = tagObject.getInt("tag_idx")
                        entityControl(QuestionEntity(null, content, tag_idx), TaskType.INSERT)
                    }
            }
        }
    }

}

private class DMLAsyncTask<TE : BaseEntity, TDO : BaseDao<TE>>(private val dao: TDO, private val taskType: TaskType) : AsyncTask<TE, Void, Void>() {
    override fun doInBackground(vararg tempEntity: TE): Void? {
        when (taskType) {
            TaskType.INSERT -> tempEntity.forEach { entity -> dao.insert(entity) }
            TaskType.UPDATE -> tempEntity.forEach { entity -> dao.update(entity) }
            TaskType.DELETE -> tempEntity.forEach { entity -> dao.delete(entity) }
            else -> {
            }
        }
        return null
    }
}

}
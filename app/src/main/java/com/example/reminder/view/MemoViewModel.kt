package com.example.reminder.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.reminder.database.entity.MemoEntity

class MemoViewModel : BaseViewModel() {

    private val dateString = MutableLiveData<String>()
    private val memoList: LiveData<List<MemoEntity>> = Transformations.switchMap(dateString, ::getMemoListByDateString)

    private fun getMemoListByDateString(dateString: String?) : LiveData<List<MemoEntity>> {
        if (dateString == null) {
            return appRepository.getAllMemo()
        }
        return appRepository.getAllMemoByDateString(dateString)
    }

    fun getMemoList(): LiveData<List<MemoEntity>> {
        return memoList
    }

    fun setDateString(dateString: String?) = apply { this.dateString.value = dateString }

    fun insertMemo(memoEntity: MemoEntity) {
        appRepository.insertMemo(memoEntity)
    }

    fun deleteMemo(memoEntity: MemoEntity) {
        appRepository.deleteMemo(memoEntity)
    }
}
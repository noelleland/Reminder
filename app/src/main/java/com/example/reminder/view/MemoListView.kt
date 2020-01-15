package com.example.reminder.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.reminder.application.App
import com.example.reminder.database.entity.MemoEntity


class MemoListView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {
    private var memoEntities: List<MemoEntity>? = null




    fun setMemoEntities(dateString: String) {
        memoEntities = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAllMemoByDateString(dateString)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}


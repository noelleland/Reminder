package com.example.remainder.timetable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.database.entity.MemoEntity
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class RoundTimetableView(context: Context?) : View(context) {

    private val memoEntity = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAll("memo") as ArrayList<MemoEntity>
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = arrayListOf(getColor(R.color.pastelRed), getColor(R.color.pastelOrange)
             ,getColor(R.color.pastelYellow), getColor(R.color.pastelGreen)
             ,getColor(R.color.pastelBlue), getColor(R.color.pastelPurple))
    private val rectF = RectF(10f, 10f, 210f, 210f)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var beforeDegree = 360f * 23 / 24
        for (index in 0 until memoEntity.size) {
            if (index == 0) {
                paint.color = getColor(R.color.pastelNight)
            }
            else {
                paint.color = colors[(index - 1) % colors.size]
            }
            canvas!!.drawArc(rectF, beforeDegree, getDegreeFromTimestamp(memoEntity[index].writeTime), true, paint)
            beforeDegree = getDegreeFromTimestamp(memoEntity[index].writeTime)
        }
    }

    private fun getColor(colorName: Int): Int {
        return ContextCompat.getColor(context!!, colorName)
    }

    private fun getDegreeFromTimestamp(timeString: String): Float {
        val pattern: Pattern = Pattern.compile(".+ ([0-9]{2}):([0-9]{2}):([0-9]{2})")
        val matcher: Matcher = pattern.matcher(timeString)
        var totalSecond = 0
        if (matcher.find()) {
            totalSecond = Integer.valueOf(matcher.group(1)!!) * 3600 + Integer.valueOf(matcher.group(2)!!) * 60 + Integer.valueOf(matcher.group(3)!!);
        }
        return totalSecond.toFloat() / 86400f * 360
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }


}
package com.example.remainder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.database.entity.MemoEntity
import java.time.LocalDate
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.abs

class RoundTimetableView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = arrayListOf(getColor(R.color.pastelRed), getColor(R.color.pastelOrange)
             ,getColor(R.color.pastelYellow), getColor(R.color.pastelGreen)
             ,getColor(R.color.pastelBlue), getColor(R.color.pastelPurple))
    private val rectF = RectF(0f, 0f
            , context.resources.displayMetrics.widthPixels.toFloat()
            , context.resources.displayMetrics.widthPixels.toFloat())
    private var memoEntities: List<MemoEntity>? = null

    init {
        memoEntities = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAllMemoByDateString(LocalDate.now().toString())
    }

    fun setMemoEntities(dateString: String) {
        memoEntities = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAllMemoByDateString(dateString)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var beforeDegree = 360f * 23 / 24 - 90
        var sweepAngle = 360f * 7 / 24
        paint.color = getColor(R.color.liteGray)
        canvas!!.drawArc(rectF, 0f, 360f, true, paint)
        for (index in 0 until memoEntities!!.size) {
            if (index == 0) {
                paint.color = getColor(R.color.pastelNight)
                canvas.drawArc(rectF, beforeDegree, sweepAngle, true, paint)
                beforeDegree = 0f
            }
            else {
                paint.color = colors[(index - 1) % colors.size]
                sweepAngle = abs(getDegreeFromTimestamp(memoEntities!![index].writeTime) - beforeDegree)
                canvas.drawArc(rectF, beforeDegree, sweepAngle, true, paint)
                beforeDegree = getDegreeFromTimestamp(memoEntities!![index].writeTime)
            }
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
        return totalSecond.toFloat() / 86400f * 360 - 90
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }


}
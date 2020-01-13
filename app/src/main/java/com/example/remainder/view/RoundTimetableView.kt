package com.example.remainder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.database.entity.MemoEntity
import java.time.LocalDate
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

class RoundTimetableView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = arrayListOf(getColor(R.color.pastelRed), getColor(R.color.pastelOrange)
            , getColor(R.color.pastelYellow), getColor(R.color.pastelGreen)
            , getColor(R.color.pastelBlue), getColor(R.color.pastelPurple))

    val rectfLength = (context.resources.displayMetrics.widthPixels - context.resources.displayMetrics.density * 60)
    private val rectF = RectF(0f, 0f, rectfLength, rectfLength)
    private var memoEntities: List<MemoEntity>? = null
    var areaSet: LinkedHashMap<Float, Float>? = LinkedHashMap()

    init {
        setMemoEntities(LocalDate.now().toString())
    }

    fun setMemoEntities(dateString: String) {
        memoEntities = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAllMemoByDateString(dateString)
    }

    override fun onDraw(canvas: Canvas?) {
        areaSet!!.clear()
        paint.color = getColor(R.color.liteGray)
        canvas!!.drawArc(rectF, 0f, 360f, true, paint)

        for (index in 0 until (memoEntities!!.size)) {
            paint.color = colors[index % colors.size]
            val startDegree = getDegreeFromTimestamp(memoEntities!![Math.floorMod(index - 1, memoEntities!!.size)].writeTime)
            val endDegree = getDegreeFromTimestamp(memoEntities!![index].writeTime)
            val sweepAngle = (((endDegree - startDegree) % 360) + 360) % 360
            areaSet!!.put(startDegree, sweepAngle)
            canvas.drawArc(rectF, startDegree, sweepAngle, true, paint)
        }
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(widthMeasureSpec))
    }

    private fun getColor(colorName: Int): Int {
        return ContextCompat.getColor(context!!, colorName)
    }

    private fun getDegreeFromTimestamp(timeString: String): Float {
        val pattern: Pattern = Pattern.compile(".+ ([0-9]{2}):([0-9]{2}):([0-9]{2})")
        val matcher: Matcher = pattern.matcher(timeString)
        var totalSecond = 0f
        if (matcher.find()) {
            totalSecond = Integer.valueOf(matcher.group(1)!!) * 3600f + Integer.valueOf(matcher.group(2)!!) * 60 + Integer.valueOf(matcher.group(3)!!);
        }
        totalSecond = totalSecond / 86400f * 360 - 90
        return ((totalSecond % 360) + 360) % 360
    }
}
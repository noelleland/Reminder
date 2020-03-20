package com.example.reminder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.reminder.R
import com.example.reminder.application.App
import com.example.reminder.database.entity.MemoEntity
import java.time.LocalDate
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.coroutines.coroutineContext

class RoundTimetableView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = arrayListOf(getColor(R.color.pastelRed), getColor(R.color.pastelOrange)
            , getColor(R.color.pastelYellow), getColor(R.color.pastelGreen)
            , getColor(R.color.pastelBlue), getColor(R.color.pastelPurple))

    val rectfLength = (context.resources.displayMetrics.widthPixels - context.resources.displayMetrics.density * 60)
    private val rectF = RectF(0f, 0f, rectfLength, rectfLength)

    var areaSet: LinkedHashMap<Float, Float>? = LinkedHashMap()

    private var memoList: LiveData<List<MemoEntity>>? = null

    fun setMemoList(memoList: LiveData<List<MemoEntity>>) {
        this.memoList = memoList
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        areaSet!!.clear()
        paint.color = getColor(R.color.liteGray)
        canvas!!.drawArc(rectF, 0f, 360f, true, paint)

        val memoEntities = memoList!!.value

        if (memoEntities != null) {
            for (index in 0 until (memoEntities.size)) {
                paint.color = colors[index % colors.size]
                val startDegree = getDegreeFromTimestamp(memoEntities[Math.floorMod(index - 1, memoEntities.size)].writeTime)
                val endDegree = getDegreeFromTimestamp(memoEntities[index].writeTime)
                val sweepAngle = floatModulo(endDegree - startDegree, 360f)
                areaSet!![startDegree] = sweepAngle
                canvas.drawArc(rectF, startDegree, sweepAngle, true, paint)
            }
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
        return floatModulo(totalSecond, 360f)
    }

    private fun floatModulo(a: Float, b: Float): Float {
        return ((a % b) + b) % b
    }

}
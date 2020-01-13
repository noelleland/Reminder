package com.example.remainder.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.size
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.application.ViewListenerManager
import com.example.remainder.database.entity.MemoEntity
import kotlinx.android.synthetic.main.fragment_managememo.view.*
import kotlinx.android.synthetic.main.fragment_timetable.*
import kotlinx.android.synthetic.main.fragment_timetable.view.*
import java.time.LocalDate
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

class TimetableFragment : Fragment() {

    private var memoEntities: List<MemoEntity>? = null
    private var lastTouchDownXY = FloatArray(2)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timetable, null)
        ViewListenerManager.setDatePickerListener(context!!, view.dateSelectButton)
        updateMemo(view)
        view.dateSelectButton.doAfterTextChanged {
            roundTimetableView.setMemoEntities(view.dateSelectButton.text.toString())
            roundTimetableView.invalidate()
            updateMemo(view)
        }
        return view
    }

    private fun updateMemo(view: View) {
        memoEntities = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance()
                .getAllMemoByDateString(view.dateSelectButton.text.toString())
        val memoList = view.timelineLinearLayout
        memoList.removeAllViews()
        for (memoEntity in memoEntities!!) {
            memoList.addView(memoEntity.getMemoView(context!!, memoList))
        }
        view.roundTimetableView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.actionMasked == MotionEvent.ACTION_DOWN) {
                    lastTouchDownXY[0] = event.rawX
                    lastTouchDownXY[1] = event.rawY
                }
                return false
            }
        })
        view.roundTimetableView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val position = IntArray(2)
                timetableLinearLayout.getLocationOnScreen(position)
                val tableCenterX = (context!!.resources.displayMetrics.widthPixels / 2)
                val tableCenterY = tableCenterX + view.dateSelectButton.bottom + position[1]
                var index = 0
                for (area in roundTimetableView.areaSet!!) {
                    if (getTouchedArch(lastTouchDownXY[0] - tableCenterX, tableCenterY - lastTouchDownXY[1], area.key, area.value)) {
                        view.timelineScrollView.scrollY = memoList.get(index * 2).y.toInt()
                        Log.i("entry", "${index}, ${memoList.get(index * 2)}")
                        return
                    }
                    index++
                }
            }
        })
    }

    private fun getTouchedArch(x: Float, y: Float, startDegree: Float, sweepAngle: Float): Boolean {
        val touchedAngle = positionToAngle(x, y)
        val touchedDistance = sqrt(x.pow(2) + y.pow(2))
        var isSection = startDegree < touchedAngle && touchedAngle < startDegree + sweepAngle
        if (startDegree + sweepAngle > 360) {
            isSection = (startDegree < touchedAngle && touchedAngle < 360)
                    || (0 < touchedAngle && touchedAngle < startDegree + sweepAngle - 360)
        }
        if (touchedDistance <= roundTimetableView.rectfLength / 2 && isSection) {
            return true
        }
        return false
    }

    private fun positionToAngle(x: Float, y: Float): Float {
        var beforeDegree = atan(y / x) * 180 / Math.PI
        if (x >= 0) {
            if (y >= 0)
                beforeDegree = 360f - beforeDegree
            else
                beforeDegree = -beforeDegree
        }
        else {
            beforeDegree = 180f - beforeDegree
        }
        return beforeDegree.toFloat()
    }
}
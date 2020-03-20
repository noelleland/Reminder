package com.example.reminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.R
import com.example.reminder.adapter.MemoListAdapter
import com.example.reminder.application.ViewListenerManager
import com.example.reminder.view.MemoViewModel
import kotlinx.android.synthetic.main.fragment_timetable.*
import kotlinx.android.synthetic.main.fragment_timetable.view.*
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

class TimetableFragment : BaseFragment() {

    private val memoListAdapter = MemoListAdapter()
    private lateinit var memoListViewModel: MemoViewModel
    private var lastTouchDownXY = FloatArray(2)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        memoListViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        memoListViewModel.getMemoList().observe(viewLifecycleOwner, Observer { memoList ->
            memoListAdapter.setMemoList(memoList)
        })

        val view = inflater.inflate(R.layout.fragment_timetable, null)
        ViewListenerManager.setDatePickerListener(context!!, view.dateSelectButton)
        updateMemo(view)
        view.dateSelectButton.doAfterTextChanged {
            updateMemo(view)
        }

        view.timeTableRecyclerView.adapter = memoListAdapter
        view.timeTableRecyclerView.layoutManager = LinearLayoutManager(context)
        view.timeTableRecyclerView.setHasFixedSize(true)

        return view
    }

    private fun updateMemo(view: View) {
        memoListViewModel.setDateString(view.dateSelectButton.text.toString())
        view.roundTimetableView.setMemoList(memoListViewModel.getMemoList())
        memoListAdapter.notifyDataSetChanged()
        view.roundTimetableView.setOnTouchListener { _, event ->
            if (event!!.actionMasked == MotionEvent.ACTION_DOWN) {
                lastTouchDownXY[0] = event.rawX
                lastTouchDownXY[1] = event.rawY
            }
            false
        }
        view.roundTimetableView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val position = IntArray(2)
                timetableLinearLayout.getLocationOnScreen(position)
                val tableCenterX = (context!!.resources.displayMetrics.widthPixels / 2)
                val tableCenterY = tableCenterX + view.dateSelectButton.bottom + position[1]
                var index = 0
                for (area in roundTimetableView.areaSet!!) {
                    if (getTouchedArch(lastTouchDownXY[0] - tableCenterX, tableCenterY - lastTouchDownXY[1], area.key, area.value)) {
                        view.timeTableRecyclerView.layoutManager!!.scrollToPosition(index)
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
        beforeDegree = if (x >= 0) {
            if (y >= 0)
                360f - beforeDegree
            else
                -beforeDegree
        } else {
            180f - beforeDegree
        }
        return beforeDegree.toFloat()
    }
}
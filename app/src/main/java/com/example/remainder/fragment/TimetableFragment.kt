package com.example.remainder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.remainder.R
import com.example.remainder.application.ViewListenerManager
import kotlinx.android.synthetic.main.fragment_timetable.*
import kotlinx.android.synthetic.main.fragment_timetable.view.*

class TimetableFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timetable, null)
        ViewListenerManager.setDatePickerListener(context!!, view.dateSelectButton)
        view.dateSelectButton.doAfterTextChanged {
            roundTimetableView.setMemoEntities(view.dateSelectButton.text.toString())
            roundTimetableView.invalidate()
        }
        return view
    }

}
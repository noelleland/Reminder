package com.example.remainder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.database.entity.MemoEntity
import com.example.remainder.timetable.RoundTimetableView
import kotlinx.android.synthetic.main.fragment_timetable.*
import kotlinx.android.synthetic.main.fragment_timetable.view.*
import java.util.ArrayList

class TimetableFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timetable, null)
        view.dateSelectButton.setOnClickListener {


        }
        return view
    }

}
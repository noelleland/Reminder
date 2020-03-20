package com.example.reminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.R
import com.example.reminder.adapter.MemoListAdapter
import com.example.reminder.application.ViewListenerManager.Companion.setDatePickerListener
import com.example.reminder.view.MemoViewModel
import kotlinx.android.synthetic.main.fragment_managememo.*
import kotlinx.android.synthetic.main.fragment_managememo.view.*

class ManageMemoFragment : BaseFragment() {

    private lateinit var memoViewModel: MemoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_managememo, null)
        setDatePickerListener(context!!, view.fromDateButton)
        setDatePickerListener(context!!, view.toDateButton)
        view.searchButton.setOnClickListener {

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memoListAdapter = MemoListAdapter()
        memoRecyclerView.adapter = memoListAdapter
        memoRecyclerView.layoutManager = LinearLayoutManager(context)
        memoRecyclerView.setHasFixedSize(true)

        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        memoViewModel.setDateString(null)
        memoViewModel.getMemoList().observe(viewLifecycleOwner, Observer { memoList -> memoListAdapter.setMemoList(memoList) })
    }

}
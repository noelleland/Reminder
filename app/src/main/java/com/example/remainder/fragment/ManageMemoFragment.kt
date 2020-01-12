package com.example.remainder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.application.ViewListenerManager.Companion.setDatePickerListener
import com.example.remainder.database.entity.MemoEntity
import kotlinx.android.synthetic.main.fragment_managememo.view.*
import kotlinx.android.synthetic.main.fragment_managememo.view.memoListLayout

class ManageMemoFragment : Fragment() {

    private val memoEntities :List<MemoEntity> = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAll("memo") as List<MemoEntity>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_managememo, null)

        setDatePickerListener(context!!, view.fromDateButton)
        setDatePickerListener(context!!, view.toDateButton)
        view.searchButton.setOnClickListener {

        }
        val memoList = view.memoScrollView.memoListLayout
        for (memoEntity in memoEntities) {
            memoList.addView(memoEntity.getMemoView(context!!, view.memoListLayout))
        }
        return view
    }

}
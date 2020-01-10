package com.example.remainder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.remainder.application.App
import com.example.remainder.activity.LockScreenActivity
import com.example.remainder.R
import com.example.remainder.application.DatabaseController
import com.example.remainder.database.entity.MemoEntity
import kotlinx.android.synthetic.main.fragment_writememo.*
import kotlinx.android.synthetic.main.fragment_writememo.view.*
import java.sql.Time
import java.time.LocalDateTime

class WriteMemoFragment : Fragment() {

    private var databaseController: DatabaseController? = null
    private var selectedQuestionIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseController = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance()

        spinner_question.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedQuestionIndex = databaseController!!.getQuestionIndexByContent(spinner_question.selectedItem.toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_writememo, null)

        view.button_memo_submit.setOnClickListener {
            val newMemo = MemoEntity(null
                        , App.globalSharedPreferences.USER_NAME.toString()
                        , Time.valueOf(LocalDateTime.now().toString())
                        , selectedQuestionIndex!!
                        , editText_memo.text.toString())
            databaseController!!.memoInsert(newMemo)

            if (activity is LockScreenActivity) {
                activity?.finish()
            }
        }
        return view
    }
}
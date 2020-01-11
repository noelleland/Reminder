package com.example.remainder.fragment

import android.os.Bundle
import android.util.Log
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
import com.example.remainder.database.entity.QuestionEntity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_writememo.*
import kotlinx.android.synthetic.main.fragment_writememo.view.*
import java.sql.Time
import java.sql.Timestamp
import java.time.LocalDateTime

class WriteMemoFragment : Fragment() {

    private var databaseController: DatabaseController? = null
    private var selectedQuestionIndex: Int? = null
    private val questionStringList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseController = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance()
        val questionList = databaseController!!.getAll("question")
        for (questionEntity in questionList) {
            questionStringList.add((questionEntity as QuestionEntity).content)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_writememo, null)

        val spinnerAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, questionStringList)
        view.spinner_question.adapter = spinnerAdapter
        view.spinner_question.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedQuestionIndex = databaseController!!.getQuestionIndexByContent(spinner_question.selectedItem.toString())
            }
        }

        view.button_memo_submit.setOnClickListener {
            val newMemo = MemoEntity(null
                        , App.globalSharedPreferences.USER_NAME.toString()
                        , Timestamp(System.currentTimeMillis()).toString()
                        , selectedQuestionIndex!!
                        , editText_memo.text.toString())
            databaseController!!.memoInsert(newMemo)
            App.globalSharedPreferences.CONNECTION_CONTROLLER.syncWithServer()
            if (activity is LockScreenActivity) {
                activity?.finish()
            }
        }
        return view
    }
}
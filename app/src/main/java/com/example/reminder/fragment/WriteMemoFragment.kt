package com.example.reminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.reminder.application.App
import com.example.reminder.R
import com.example.reminder.application.DatabaseController
import com.example.reminder.database.entity.MemoEntity
import com.example.reminder.database.entity.QuestionEntity
import kotlinx.android.synthetic.main.fragment_writememo.*
import kotlinx.android.synthetic.main.fragment_writememo.view.*
import java.sql.Timestamp

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
            editText_memo.text.clear()
            Toast.makeText(context!!, "메모가 저장되었어요!", Toast.LENGTH_SHORT).show()
            App.globalSharedPreferences.CONNECTION_CONTROLLER.syncWithServer()
        }
        return view
    }
}
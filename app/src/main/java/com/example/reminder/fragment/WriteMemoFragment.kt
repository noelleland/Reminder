package com.example.reminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reminder.application.App
import com.example.reminder.R
import com.example.reminder.database.entity.MemoEntity
import com.example.reminder.database.entity.QuestionEntity
import com.example.reminder.view.MemoViewModel
import com.example.reminder.view.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_writememo.*
import kotlinx.android.synthetic.main.fragment_writememo.view.*
import kotlinx.coroutines.*
import java.sql.Timestamp

class WriteMemoFragment : BaseFragment() {

    private var selectedQuestionIndex: Int = 0
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_writememo, null)

        val spinnerAdapter = ArrayAdapter<QuestionEntity>(context!!, android.R.layout.simple_spinner_dropdown_item)
        questionViewModel.getQuestionList().observe(viewLifecycleOwner, Observer { list ->
            list.forEach {
                spinnerAdapter.add(it)
            }
        })
        view.spinner_question.adapter = spinnerAdapter
        view.spinner_question.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedQuestionIndex = (spinner_question.selectedItem as QuestionEntity).idx!!
            }
        }

        view.button_memo_submit.setOnClickListener {
            val newMemo = MemoEntity(null
                    , App.globalSharedPreferences.USER_NAME.toString()
                    , Timestamp(System.currentTimeMillis()).toString()
                    , selectedQuestionIndex
                    , editText_memo.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                memoViewModel.insertMemo(newMemo)
            }

            editText_memo.text.clear()
            Toast.makeText(context!!, "메모가 저장되었어요!", Toast.LENGTH_SHORT).show()
            //App.globalSharedPreferences.CONNECTION_CONTROLLER.syncWithServer()
        }
        return view
    }
}
package com.example.remainder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remainder.LockScreenActivity
import com.example.remainder.R
import com.example.remainder.database.MemoDB
import com.example.remainder.database.entity.MemoEntity
import kotlinx.android.synthetic.main.fragment_writememo.*
import kotlinx.android.synthetic.main.fragment_writememo.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteMemoFragment : Fragment() {

    private var memoDB: MemoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_writememo, null)


        view.button_memo_submit.setOnClickListener {
            val writeRunnable = Runnable {
                val newMemo = MemoEntity(null,
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                    editText_memo.text.toString())
                memoDB?.MemoDao()?.insert(newMemo)
            }
            if (activity is LockScreenActivity) {
                activity?.finish()
            }
        }
        return view
    }
}
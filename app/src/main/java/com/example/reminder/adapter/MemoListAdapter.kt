package com.example.reminder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.database.entity.MemoEntity

class MemoListAdapter : RecyclerView.Adapter<MemoListAdapter.ViewHolder>() {
    private var memoList: List<MemoEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_entity, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(memoList[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val questionTextView = itemView.findViewById<TextView>(R.id.questionText)
        private val writeTimeTextView = itemView.findViewById<TextView>(R.id.writeTimeText)
        private val answerTextView = itemView.findViewById<TextView>(R.id.answerText)

        fun bind(memo: MemoEntity) {
            questionTextView.text =memo.question_idx.toString()
            writeTimeTextView.text = memo.writeTime
            answerTextView.text = memo.answer

            itemView.setOnClickListener {
            }

            itemView.setOnLongClickListener {
                memoList
                true
            }
        }
    }

    fun setMemoList(memoList: List<MemoEntity>) {
        this.memoList = memoList
        notifyDataSetChanged()
    }

}
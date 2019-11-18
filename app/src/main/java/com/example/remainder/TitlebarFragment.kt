package com.example.remainder

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TitlebarFragment : Fragment() {

    private var button_monthSelect: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_titlebar, container, false)
        return view
    }

    override fun onInflate(context: Context?, attrs: AttributeSet?, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        if (context != null && attrs != null) {
            /*
            val ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
            if (ta.hasValue(R.styleable.TitleBar_title)) {
                title = ta.getString(R.styleable.TitleBar_title)
            }
            if (ta.hasValue(R.styleable.TitleBar_leftButton)) {
                button_monthSelect = ta.getResourceId(R.styleable.TitleBar_leftButton, 0)
            }
            ta.recycle()
            */
        }
    }
}
package com.example.remainder.application;

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import java.time.LocalDate
import java.util.*

class ViewListenerManager {

    companion object {
        fun setDatePickerListener(context: Context, button: Button) {
            if (button.text.none()) {
                button.text = "${LocalDate.now()}"
            }
            button.setOnClickListener {
                val datePickerDialog = DatePickerDialog(context
                        , DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    button.text = String.format("%4d-%02d-%02d", year, month + 1, dayOfMonth)
                }, Integer.valueOf(button.text.substring(0, 4))
                        , Integer.valueOf(button.text.substring(5, 7)) - 1
                        , Integer.valueOf(button.text.substring(8, 10)))
                datePickerDialog.show()
            }
        }
    }
}

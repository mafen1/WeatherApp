package com.example.myweather.core

import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class Extension {

    fun updateText(view: TextView, message: Any){
        view.text = message.toString()
    }
    fun makeSnackBarMessage(view: View, message: String){
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
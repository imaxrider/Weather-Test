package com.imax.testapplication.compoment

import android.app.AlertDialog
import android.content.Context
import android.text.Html
import com.imax.testapplication.R

object Alert {
    fun isErrorDialog(context: Context?, message: String?) : AlertDialog.Builder {

        val alert = AlertDialog.Builder(context)
        alert.setTitle("Error")
        alert.setIcon(R.drawable.ic_error)
        alert.setMessage(message)
        alert.create()
        alert.setCancelable(false)
        alert.setNegativeButton("Close") { _, _ -> }
        return alert
    }

    fun isSuccessDialog(context: Context?,  message: String?) : AlertDialog.Builder {
        val  alert = AlertDialog.Builder(context)
        alert.setTitle("Success")
        alert.setIcon(R.drawable.ic_success)
        alert.setMessage(message)
        alert.create()
        alert.setCancelable(false)

        return alert
    }
}
package com.imax.testapplication.compoment

import android.app.ProgressDialog
import android.content.Context

object Loading {
    private lateinit var progressDialog : ProgressDialog

    fun progressDialogShow(context: Context){
        progressDialog = ProgressDialog(context)
       // progressDialog.setTitle("Please wait..")
        progressDialog.setCancelable(false)
        progressDialog.show()

    }
    fun progressDialogDismiss(){
        progressDialog.dismiss()
    }
}
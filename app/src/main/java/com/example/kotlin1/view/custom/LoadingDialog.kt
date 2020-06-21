package com.example.kotlin1.view.custom

import android.app.Activity
import android.app.AlertDialog
import com.example.kotlin1.R

object LoadingDialog {

    private var dialog: AlertDialog? = null

    fun show(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        builder.setView(inflater?.inflate(R.layout.custom_loading, null))

        dialog = builder.create()
        dialog?.show()
    }

    fun hide() {
        dialog?.dismiss()
    }


}
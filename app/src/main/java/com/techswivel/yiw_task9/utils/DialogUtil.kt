package com.techswivel.yiw_task9.utils

import android.app.AlertDialog
import android.content.Context
import com.techswivel.yiw_task9.R

object DialogUtil {


    fun showAppSettingDialog(context: Context, buttonListener: DialogButtonListener) {
        val builder = AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.permission_alert))
            .setMessage(context.getString(R.string.permission_dialog_message))
            .setCancelable(true)
        setSettingDialogButtons(context, builder, buttonListener)
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setSettingDialogButtons(
        context: Context,
        builder: AlertDialog.Builder,
        buttonListener: DialogButtonListener
    ) {
        builder.setPositiveButton(context.getString(R.string.goto_setting)) { dialog, id ->
            buttonListener.onPositiveClick()
            dialog.dismiss()
        }
        builder.setNegativeButton(context.getString(R.string.cancel)) { dialog, id ->
            buttonListener.onNegativeClick()
            dialog.dismiss()
        }
    }

    interface DialogButtonListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

}
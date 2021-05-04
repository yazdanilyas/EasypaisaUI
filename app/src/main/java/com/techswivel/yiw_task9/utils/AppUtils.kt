package com.techswivel.yiw_task9.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techswivel.yiw_task9.R

object AppUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun openAppSetting(context: Context, activity: AppCompatActivity) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri: Uri = Uri.parse(context.getString(R.string.package_str) + activity.packageName)
        intent.data = uri
        context.startActivity(intent)
    }
}
package com.techswivel.yiw_task9.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.application.MyApplication

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

    fun loadImageWithGlide(img: AppCompatImageView, url: Int) {
        val circularProgressDrawable = img.context?.let { CircularProgressDrawable(it) }
        circularProgressDrawable?.strokeWidth = 5f
        circularProgressDrawable?.centerRadius = 30f
        circularProgressDrawable?.start()
        Glide.with(MyApplication.getAppContext())
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_broken_image)
            .placeholder(circularProgressDrawable)
            .into(img)
    }
}
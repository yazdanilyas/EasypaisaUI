package com.techswivel.yiw_task9.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.application.MyApplication


@BindingAdapter("setImageUrl")
fun setImageUrl(img: AppCompatImageView, url: Int) {
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
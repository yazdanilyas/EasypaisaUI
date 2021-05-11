package com.techswivel.yiw_task9.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("setImageUrl")
fun setImageUrl(img: AppCompatImageView, url: Int) {
    AppUtils.loadImageWithGlide(img, url)
}
package com.techswivel.yiw_task9.models

data class MobilePackage(
    val packageName: String,
    val packageDiscount: String,
    val packageValidity: String,
    val onNetMins: Int,
    val offNetMins: Int,
    val sms: Int,
    val gbs: Float,
    val price: String,
    val validityMsg: String,

    ) {
}
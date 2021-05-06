package com.techswivel.yiw_task9.application

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        private lateinit var context: Context
        fun getAppContext(): Context {
            return context
        }
    }
}
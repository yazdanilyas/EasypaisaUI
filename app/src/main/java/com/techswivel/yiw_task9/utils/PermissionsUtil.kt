package com.techswivel.yiw_task9.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat


object PermissionsUtil {
    const val REQUEST_CODE_WRITE_STORAGE = 100
    const val REQUEST_CODE_READ_STORAGE = 101
    const val REQUEST_CODE_CAMERA = 103
    fun hasPermission(activity: Activity, permissions: Array<String>, requestCode: Int): Boolean {

        return if (ActivityCompat.checkSelfPermission(
                activity,
                permissions[0]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForPermission(activity, permissions, requestCode)
            false
        } else true
    }

    private fun askForPermission(
        activity: Activity,
        permission: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(activity, permission, requestCode)
    }

    fun isMarshMallowOrAbove(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

}
package com.sbz.musicnote.utils

import android.Manifest
import android.os.Build

object Constants {
    const val DELAY_TIME = 2000L
    const val PERMISSION_REQ_CODE = 1
    val PERMISSIONS_REQUIRED =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_AUDIO,
                /*Manifest.permission.MANAGE_EXTERNAL_STORAGE*/
            )
        } else {
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
}

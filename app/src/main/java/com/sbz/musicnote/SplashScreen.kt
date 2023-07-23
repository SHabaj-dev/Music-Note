package com.sbz.musicnote

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sbz.musicnote.utils.Constants

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (arePermissionGranted()) {
            goToMainActivity()
        } else {
            askPermissions()
        }


    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(
            this,
            Constants.PERMISSIONS_REQUIRED,
            Constants.PERMISSION_REQ_CODE
        )
    }

    private fun goToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                finish()
            }, Constants.DELAY_TIME
        )
    }

    private fun arePermissionGranted(): Boolean {
        for (permission in Constants.PERMISSIONS_REQUIRED) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}
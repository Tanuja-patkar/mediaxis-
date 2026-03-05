package com.medaxis.ui.common

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.medaxis.R
import com.medaxis.ui.auth.AuthActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 2000)
    }
}

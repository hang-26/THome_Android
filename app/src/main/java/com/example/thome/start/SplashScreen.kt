package com.example.thome.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.thome.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* Thread.sleep(5000)
        installSplashScreen()*/
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val i = Intent(this, OnBoardingActivity::class.java)
            startActivity(i)
            finish()
        },3000)

    }
}
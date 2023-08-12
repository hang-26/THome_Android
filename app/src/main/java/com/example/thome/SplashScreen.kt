package com.example.thome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* Thread.sleep(5000)
        installSplashScreen()*/
        setContentView(R.layout.splash_screen)
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}
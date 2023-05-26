package com.example.muweiz.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muweiz.ui.login.login.Login
import com.example.muweiz.ui.login.login_register.login_register_Activity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        super.onCreate(savedInstanceState)
        //startActivity(Intent(this, login_register_Activity::class.java))
        startActivity(Intent(this,MainActivity::class.java))
    }
}
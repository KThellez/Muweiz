package com.example.muweiz.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muweiz.ui.login.login_register.LoginAndRegister


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        super.onCreate(savedInstanceState)
        //startActivity(Intent(this, MainActivity::class.java))
        startActivity(Intent(this,LoginAndRegister::class.java))
    }
}
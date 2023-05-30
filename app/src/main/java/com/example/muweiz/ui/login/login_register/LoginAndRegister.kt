package com.example.muweiz.ui.login.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.muweiz.data.model.LoginSimple.LoginS
import com.example.muweiz.data.model.LoginSimple.SingUpS
import com.example.muweiz.databinding.ActivityLoginAndRegisterBinding
import com.example.muweiz.databinding.ActivityLoginBinding

class LoginAndRegister : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAndRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAndRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSingIn = binding.btnSingIn
        btnSingIn.setOnClickListener{
            startActivity(Intent(this,LoginS::class.java))
        }

        val btnSingUP = binding.btnSingUp
        btnSingUP.setOnClickListener{
            startActivity(Intent(this,SingUpS::class.java))
        }
    }
}
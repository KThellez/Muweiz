package com.example.muweiz.ui.login.login_register
/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.muweiz.data.model.LoginSimple.LoginS
import com.example.muweiz.databinding.ActivityLoginRegisterBinding
import com.example.muweiz.ui.login.signin.SignIn
import com.example.muweiz.ui.viewModel.login.login_register_viewModel
import dagger.hilt.android.AndroidEntryPoint


class login_register_Activity : AppCompatActivity() {
    private lateinit var  binding: ActivityLoginRegisterBinding
    private val login_signin: login_register_viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            btnLogin.setOnClickListener { login_signin.onLoginSelected() }
            btnSingIn.setOnClickListener { login_signin.onSignInSelected() }
        }
    }
    private fun initObservers() {
        login_signin.navigateToLogin.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToLogin()
            }
        })
        login_signin.navigateToSignIn.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToSingIn()
            }
        })
    }


    private fun goToSingIn() {
       startActivity(SignIn.create(this))
    }

    private fun goToLogin() {
        startActivity(LoginS.create(this))
    }
}*/
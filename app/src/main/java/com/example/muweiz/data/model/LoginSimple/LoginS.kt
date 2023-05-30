package com.example.muweiz.data.model.LoginSimple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.muweiz.data.extention.dismissKeyboard
import com.example.muweiz.data.extention.toast
import com.example.muweiz.databinding.ActivityLoginBinding
import com.example.muweiz.databinding.ActivityLoginSBinding
import com.example.muweiz.ui.view.MainActivity
import com.example.muweiz.ui.viewModel.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginS : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginSBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseStateAuth: FirebaseAuth.AuthStateListener
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbLoading.isVisible = false

        firebaseAuth = Firebase.auth
        val btnIngresar = binding.btnLogin
        val btnRegistrarse = binding.tvForgotPassword
        val txtEmail  = binding.etEmail
        val txtPassword  = binding.etPassword

        btnIngresar.setOnClickListener {
            if (txtEmail.text.toString() != null && txtPassword.text.toString() != null){
                /*it.dismissKeyboard()
                loginViewModel.onLoginSelected(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )*/
                binding.pbLoading.isVisible = true
                singIn(txtEmail.text.toString(), txtPassword.text.toString())
            }
        }
        btnRegistrarse.setOnClickListener {
            startActivity(Intent(this, SingUpS::class.java))
        }



    }
    private fun singIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    toast("Ingreso Correctamente", Toast.LENGTH_SHORT)
                    binding.pbLoading.isVisible = false
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else{
                    toast("Error de Email o Password", Toast.LENGTH_SHORT)
                    binding.pbLoading.isVisible = false
                }
            }

    }
}
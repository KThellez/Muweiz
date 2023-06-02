package com.example.muweiz.data.model.LoginSimple

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.muweiz.R
import com.example.muweiz.data.extention.toast
import com.example.muweiz.databinding.ActivityLoginSBinding
import com.example.muweiz.ui.view.MainActivity
import com.example.muweiz.ui.viewModel.login.LoginViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginS : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginSBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseStateAuth: FirebaseAuth.AuthStateListener
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var signInRequest: BeginSignInRequest


    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true


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


        binding.btnLoginGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }
    private fun singIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    toast(getString(R.string.SuccessSignIn), Toast.LENGTH_SHORT)
                    binding.pbLoading.isVisible = false
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else{
                    toast(getString(R.string.NoSuccessSign), Toast.LENGTH_SHORT)
                    binding.pbLoading.isVisible = false
                }
            }

    }
    private fun signInWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this) { signInTask ->
                            if (signInTask.isSuccessful) {
                                val user = firebaseAuth.currentUser
                                Toast.makeText(this, getString(R.string.SuccessSignInGoogle), Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, getString(R.string.NoSuccessSignInGoogle), Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, getString(R.string.NoSuccessSignInGoogle), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }

}
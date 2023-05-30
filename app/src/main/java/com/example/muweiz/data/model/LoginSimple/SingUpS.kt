package com.example.muweiz.data.model.LoginSimple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.muweiz.data.extention.dismissKeyboard
import com.example.muweiz.data.extention.toast
import com.example.muweiz.data.model.login.UserSignIn
import com.example.muweiz.data.network.FirebaseClient
import com.example.muweiz.data.network.UsuarioServicio
import com.example.muweiz.databinding.ActivitySingUpSBinding
import com.example.muweiz.ui.view.MainActivity
import com.example.muweiz.ui.viewModel.signin.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SingUpS : AppCompatActivity() {
    private lateinit var binding: ActivitySingUpSBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseStateAuth: FirebaseAuth.AuthStateListener
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpSBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbLoading.isVisible = false

        val Nickname = binding.etNickName
        val realname = binding.etRealName
        val email = binding.etEmail
        val password1 = binding.etPassword
        val password2 = binding.etRepeatPassword
        val btnCrear = binding.btnCreateAccount
        firebaseAuth  = Firebase.auth

        btnCrear.setOnClickListener {
            val p1 = password1.text.toString()
            val p2 = password2.text.toString()

            if(Nickname.text.toString().length > 3){
                if (realname != null ){
                    if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                        if (p1.equals(p2)){
                            /*it.dismissKeyboard()
                            signInViewModel.onSignInSelected(
                                UserSignIn(
                                    realName = realname.text.toString(),
                                    nickName = Nickname.text.toString(),
                                    email = email.text.toString(),
                                    password = password1.text.toString(),
                                    passwordConfirmation = password2.text.toString()
                                )
                            )*/
                            binding.pbLoading.isVisible = true
                            createAccount(
                                nickname = Nickname.text.toString(),
                                realname = realname.text.toString(),
                                email = email.text.toString(),
                                p1 = password1.text.toString(),
                                p2 = password2.text.toString()
                            )
                        }else{
                            toast("Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                            password2.requestFocus()
                        }
                    }else{
                        toast("Ingrese un correo electrónico válido", Toast.LENGTH_SHORT)
                        email.requestFocus()
                    }
                }else{
                    toast("Digite su nombre completo", Toast.LENGTH_SHORT)
                    Nickname.requestFocus()
                }
            }else{
                toast("El Nombre publico deber tener minimo 4 caracteres", Toast.LENGTH_SHORT)
                Nickname.requestFocus()
            }

        }
    }

    private fun createAccount(nickname: String, realname: String, email: String, p1: String, p2: String) {
        lifecycleScope.launch { // Envuelve la llamada en lifecycleScope.launch
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, p1).await()
                toast("Cuenta Creada Correctamente", Toast.LENGTH_SHORT)
                createUserTable(nickname, realname, email)
            } catch (e: Exception) {
                // Maneja cualquier excepción que pueda ocurrir
                toast("Error al crear la cuenta: ${e.message}", Toast.LENGTH_SHORT)
                binding.pbLoading.isVisible = false
            }
        }
    }

    private suspend fun createUserTable(nickname: String, realname: String, email: String) {
        val user = hashMapOf(
            "email" to email,
            "nickname" to nickname,
            "realname" to realname
        )

        try {
            val firebaseClient = FirebaseClient()
            firebaseClient.db
                .collection(UsuarioServicio.USER_COLLECTION)
                .add(user).await()
            binding.pbLoading.isVisible = false
            startActivity(Intent(this, LoginS::class.java))
        } catch (e: Exception) {
            // Maneja cualquier excepción que pueda ocurrir
            toast("Error al crear la tabla de usuario: ${e.message}", Toast.LENGTH_SHORT)
        }
    }
}
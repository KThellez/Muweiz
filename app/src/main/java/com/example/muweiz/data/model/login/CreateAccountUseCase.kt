package com.example.muweiz.data.model.login

import com.example.muweiz.data.network.ServicioAutentificacion
import com.example.muweiz.data.network.UsuarioServicio
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: ServicioAutentificacion,
    private val userService: UsuarioServicio
) {

    suspend operator fun invoke(userSignIn: UserSignIn): Boolean {
        val accountCreated =
            authenticationService.createAccount(userSignIn.email, userSignIn.password) != null
        return if (accountCreated) {
            userService.createUserTable(userSignIn)
        } else {
            false
        }
    }
}
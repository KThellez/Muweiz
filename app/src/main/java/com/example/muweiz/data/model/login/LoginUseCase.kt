package com.example.muweiz.data.model.login

import com.example.muweiz.data.network.LoginResult
import com.example.muweiz.data.network.ServicioAutentificacion
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: ServicioAutentificacion) {
    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)
}
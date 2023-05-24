package com.example.muweiz.ui.viewModel.verificacion

import com.example.muweiz.data.network.ServicioAutentificacion
import javax.inject.Inject

class SendEmailVerificationUseCase @Inject constructor(private val authenticationService: ServicioAutentificacion) {
    suspend operator fun invoke() = authenticationService.sendVerificationEmail()
}
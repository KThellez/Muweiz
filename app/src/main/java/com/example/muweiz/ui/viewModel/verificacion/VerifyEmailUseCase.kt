package com.example.muweiz.ui.viewModel.verificacion

import com.example.muweiz.data.network.ServicioAutentificacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(private val authenticationService: ServicioAutentificacion) {
    operator fun invoke(): Flow<Boolean> = authenticationService.verifiedAccount
}
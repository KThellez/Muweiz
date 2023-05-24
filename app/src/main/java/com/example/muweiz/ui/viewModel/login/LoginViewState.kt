package com.example.muweiz.ui.viewModel.login

data class LoginViewState (
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true
)
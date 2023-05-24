package com.example.muweiz.ui.viewModel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muweiz.data.model.Event
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class login_register_viewModel @Inject constructor(): ViewModel(){

    private val _navigateToLogin = MutableLiveData<Event<Boolean>>()

    val navigateToLogin: LiveData<Event<Boolean>>
        get() = _navigateToLogin

    private val _navigateToSignIn = MutableLiveData<Event<Boolean>>()
    val navigateToSignIn: LiveData<Event<Boolean>>
        get() = _navigateToSignIn

    fun onLoginSelected() {
        _navigateToLogin.value = Event(true)
    }

    fun onSignInSelected() {
        _navigateToSignIn.value = Event(true)
    }
}
package com.example.muweiz.ui.login.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.aristidevs.nuwelogin.core.dialog.DialogFragmentLauncher
import com.aristidevs.nuwelogin.core.dialog.ErrorDialog
import com.aristidevs.nuwelogin.core.dialog.LoginSuccessDialog
import com.example.muweiz.R
import com.example.muweiz.data.extention.*
import com.example.muweiz.data.model.login.UserLogin
import com.example.muweiz.databinding.ActivityLoginBinding
import com.example.muweiz.databinding.ActivityLoginRegisterBinding
import com.example.muweiz.ui.login.signin.SignIn
import com.example.muweiz.ui.view.Verification
import com.example.muweiz.ui.viewModel.login.LoginViewModel
import com.example.muweiz.ui.viewModel.login.LoginViewState
import com.example.muweiz.ui.viewModel.login.login_register_viewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class Login : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, Login::class.java)

    }

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()

    }

    private fun initListeners() {
        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.onTextChanged { onFieldChanged() }

        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etPassword.onTextChanged { onFieldChanged() }

        binding.tvForgotPassword.setOnClickListener { loginViewModel.onForgotPasswordSelected() }

        binding.btnLogin.setOnClickListener {
            it.dismissKeyboard()
            loginViewModel.onLoginSelected(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun initObservers() {
        loginViewModel.navigateToDetails.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToDetail()
            }
        }

        loginViewModel.navigateToSignIn.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToSignIn()
            }
        }

        loginViewModel.navigateToForgotPassword.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToForgotPassword()
            }
        }

        loginViewModel.navigateToVerifyAccount.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToVerify()
            }
        }

        loginViewModel.showErrorDialog.observe(this) { userLogin ->
            if (userLogin.showErrorDialog) showErrorDialog(userLogin)
        }

        lifecycleScope.launchWhenStarted {
            loginViewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun updateUI(viewState: LoginViewState) {
        with(binding) {
            pbLoading.isVisible = viewState.isLoading
            tilEmail.error =
                if (viewState.isValidEmail) null else getString(R.string.login_error_mail)
            tilPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.login_error_password)
        }
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            loginViewModel.onFieldsChanged(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
    }

    private fun showErrorDialog(userLogin: UserLogin) {
        ErrorDialog.create(
            title = getString(R.string.login_error_dialog_title),
            description = getString(R.string.login_error_dialog_body),
            negativeAction = ErrorDialog.Action(getString(R.string.login_error_dialog_negative_action)) {
                it.dismiss()
            },
            positiveAction = ErrorDialog.Action(getString(R.string.login_error_dialog_positive_action)) {
                loginViewModel.onLoginSelected(
                    userLogin.email,
                    userLogin.password
                )
                it.dismiss()
            }
        ).show(dialogLauncher, this)
    }

    private fun goToForgotPassword() {
        toast(getString(R.string.feature_not_allowed))
    }

    private fun goToSignIn() {
        startActivity(SignIn.create(this))
    }

    private fun goToDetail() {
        LoginSuccessDialog.create().show(dialogLauncher, this)
    }

    private fun goToVerify() {
        startActivity(Verification.create(this))
    }
    private fun goToSplash() {
        //
    }
}
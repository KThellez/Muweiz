package com.example.muweiz.ui.login.signin

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
import com.example.muweiz.R
import com.example.muweiz.data.extention.dismissKeyboard
import com.example.muweiz.data.extention.loseFocusAfterAction
import com.example.muweiz.data.extention.onTextChanged
import com.example.muweiz.data.extention.show
import com.example.muweiz.data.model.login.UserSignIn
import com.example.muweiz.databinding.ActivitySignInBinding
import com.example.muweiz.ui.login.login.Login
import com.example.muweiz.ui.view.Verification
import com.example.muweiz.ui.viewModel.signin.SignInViewModel
import com.example.muweiz.ui.viewModel.signin.SignInViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class   SignIn : AppCompatActivity() {
    companion object {
        fun create(context: Context): Intent =
            Intent(context, SignIn::class.java)
    }

    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {

        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etEmail.onTextChanged { onFieldChanged() }

        binding.etNickName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etNickName.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etNickName.onTextChanged { onFieldChanged() }

        binding.etRealName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etRealName.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etRealName.onTextChanged { onFieldChanged() }

        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etPassword.onTextChanged { onFieldChanged() }

        binding.etRepeatPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etRepeatPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etRepeatPassword.onTextChanged { onFieldChanged() }


        with(binding) {
            btnCreateAccount.setOnClickListener {
                it.dismissKeyboard()
                signInViewModel.onSignInSelected(
                    UserSignIn(
                        realName = binding.etRealName.text.toString(),
                        nickName = binding.etNickName.text.toString(),
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString(),
                        passwordConfirmation = binding.etRepeatPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun initObservers() {
        signInViewModel.navigateToVerifyEmail.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToVerifyEmail()
            }
        }

        signInViewModel.navigateToLogin.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToLogin()
            }
        }

        lifecycleScope.launchWhenStarted {
            signInViewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }

        signInViewModel.showErrorDialog.observe(this) { showError ->
            if (showError) showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        ErrorDialog.create(
            title = getString(R.string.signin_error_dialog_title),
            description = getString(R.string.signin_error_dialog_body),
            positiveAction = ErrorDialog.Action(getString(R.string.signin_error_dialog_positive_action)) {
                it.dismiss()
            }
        ).show(dialogLauncher, this)
    }

    private fun updateUI(viewState: SignInViewState) {
        with(binding) {
            pbLoading.isVisible = viewState.isLoading
            binding.tilEmail.error =
                if (viewState.isValidEmail) null else getString(R.string.signin_error_mail)
            binding.tilNickName.error =
                if (viewState.isValidNickName) null else getString(R.string.signin_error_nickname)
            binding.tilRealName.error =
                if (viewState.isValidRealName) null else getString(R.string.signin_error_realname)
            binding.tilPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
            binding.tilRepeatPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
        }
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            signInViewModel.onFieldsChanged(
                UserSignIn(
                    realName = binding.etRealName.text.toString(),
                    nickName = binding.etNickName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString(),
                    passwordConfirmation = binding.etRepeatPassword.text.toString()
                )
            )
        }
    }

    private fun goToVerifyEmail() {
        startActivity(Verification.create(this))
    }

    private fun goToLogin() {
        startActivity(Login.create(this))
    }
}
package com.example.muweiz.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.aristidevs.nuwelogin.core.dialog.DialogFragmentLauncher
import com.aristidevs.nuwelogin.core.dialog.LoginSuccessDialog
import com.example.muweiz.data.extention.show
import com.example.muweiz.databinding.ActivityVerificationBinding
import com.example.muweiz.ui.viewModel.verificacion.VerificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class Verification : AppCompatActivity() {
    companion object {
        fun create(context: Context): Intent =
            Intent(context, Verification::class.java)
    }

    @Inject
    lateinit var dialogLauncher: DialogFragmentLauncher

    private lateinit var binding: ActivityVerificationBinding
    private val verificationViewModel: VerificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.btnGoToDetail.setOnClickListener { verificationViewModel.onGoToDetailSelected() }
    }

    private fun initObservers() {
        verificationViewModel.navigateToVerifyAccount.observe(this) {
            it.getContentIfNotHandled()?.let {
                LoginSuccessDialog.create().show(dialogLauncher, this)
            }
        }

        verificationViewModel.showContinueButton.observe(this) {
            it.getContentIfNotHandled()?.let {
                binding.btnGoToDetail.isVisible = true
            }
        }
    }
}
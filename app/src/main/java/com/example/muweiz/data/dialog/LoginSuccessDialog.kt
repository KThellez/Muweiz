package com.aristidevs.nuwelogin.core.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.muweiz.databinding.ActivityMainBinding
import com.example.muweiz.databinding.DialogLoginSuccessBinding


class LoginSuccessDialog : DialogFragment() {

    companion   object {
        fun create(): LoginSuccessDialog = LoginSuccessDialog()
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogLoginSuccessBinding.inflate(requireActivity().layoutInflater)
        binding.btnPositive.setOnClickListener {
            binding.btnPositive.setOnClickListener {
                dismiss()
                //val intent = Intent(requireContext(), ActivityMainBinding::class.java)
                //startActivity(intent)
            }
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(true)
            .create()
    }
}
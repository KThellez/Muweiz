package com.example.muweiz.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.muweiz.data.extention.toast
import com.example.muweiz.databinding.ActivityLoginAndRegisterBinding
import com.example.muweiz.databinding.ActivityVerificationBinding
import com.example.muweiz.databinding.ActivityWorkInPogressBinding

class WorkInPogress : AppCompatActivity() {
    private lateinit var binding: ActivityWorkInPogressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkInPogressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toast("Gracias!", Toast.LENGTH_SHORT)
    }
}
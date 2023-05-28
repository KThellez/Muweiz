package com.example.muweiz.data.network

import android.content.Intent

interface AuthResultListener {
    fun onAuthResult(requestCode: Int, resultCode: Int, data: Intent?)
}

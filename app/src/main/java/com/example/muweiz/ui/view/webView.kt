package com.example.muweiz.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.muweiz.BuildConfig
import com.example.muweiz.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var binding: ActivityWebViewBinding
    private val CLIENT_ID = BuildConfig.API_C
    private val REDIRECT_URI = BuildConfig.URI

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView

        // Configurar el WebView
        webView.settings.javaScriptEnabled = true

        // Cargar la URL de autenticación de Spotify
        val spotifyAuthUrl = "https://accounts.spotify.com/authorize?client_id=$CLIENT_ID&response_type=token&redirect_uri=$REDIRECT_URI&scope=streaming"
        webView.loadUrl(spotifyAuthUrl)

        // Configurar el WebViewClient para manejar las redirecciones dentro de la aplicación
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }

    }
}
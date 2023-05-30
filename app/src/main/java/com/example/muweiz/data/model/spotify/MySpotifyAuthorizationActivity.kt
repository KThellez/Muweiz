    package com.example.muweiz.data.model.spotify

    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
    import android.util.Log
    import android.webkit.WebView
    import androidx.appcompat.app.AppCompatActivity


    class MySpotifyAuthorizationActivity : AppCompatActivity() {
        private lateinit var webView: WebView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }
        /*override fun onNewIntent(intent: Intent) {
            super.onNewIntent(intent)
            val uri: Uri? = intent.data
            if (uri != null) {
                val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)
                Log.d("MySpotifyAuthActivity", "Received authorization response. Type: ${response.type}")
                when (response.type) {
                    AuthorizationResponse.Type.TOKEN -> {
                        val authToken: String? = response.accessToken
                        Log.d("MySpotifyAuthActivity", "Access token: $authToken")
                        // Manejar la respuesta exitosa con el token de acceso
                    }
                    AuthorizationResponse.Type.ERROR -> {
                        val error: String? = response.error
                        val errorDescription: String? = response.error.toString()
                        Log.d("MySpotifyAuthActivity", "Error during authorization. Error: $error, Description: $errorDescription")
                        // Manejar la respuesta de error
                    }
                    else -> {
                        Log.d("MySpotifyAuthActivity", "Other authorization response type")
                        // Manejar otros casos
                    }
                }
            }
        }*/
    }
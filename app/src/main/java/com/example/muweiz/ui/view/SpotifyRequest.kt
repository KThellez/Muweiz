package com.example.muweiz.ui.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.BuildConfig
import com.example.muweiz.R
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.example.muweiz.data.ServidorLocal.ServidorLocal
import com.example.muweiz.data.network.*
import com.example.muweiz.databinding.ActivitySpotifyRequestBinding
import com.spotify.android.appremote.api.error.SpotifyRemoteServiceException
import kotlin.coroutines.cancellation.CancellationException

class SpotifyRequest : AppCompatActivity(), AuthResultListener {

    private val CLIENT_ID = BuildConfig.API_C
    private val redirectUri = BuildConfig.URI
    private lateinit var btnBuscar: SearchView
    private lateinit var songData: SongData
    private lateinit var buscarSong: BusquedaSpotify
    private lateinit var T1: String
    private lateinit var spotifyAppRemote: SpotifyAppRemote
    private lateinit var binding: ActivitySpotifyRequestBinding
    private lateinit var server: ServidorLocal
    private lateinit var spotifyAuthenticator: SpotifyAuthenticator
    private lateinit var token: String

    //private val GOOGLE_REDIRECT_URI = "https://muweiz-533c5.firebaseapp.com/__/auth/handler"
    private val GOOGLE_REDIRECT_URI = redirectUri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpotifyRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnBuscar = binding.buscador
        songData = SongData(R.drawable.piano, "SRV", "Pride and Joy", "Eb Major", "130", "77")
        //server = ServidorLocal("192.168.0.9", 7777, this)
        //server.startServer()

        spotifyAuthenticator = SpotifyAuthenticator(this)


        spotifyAuthenticator.authenticate { isAuthenticated ->
            spotifyAuthenticator.setAuthResultListener(this)
            if (isAuthenticated) {
                val token = traerToken()
                T1 = token
                btnBuscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        performSearch(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        println(newText)
                        return false
                    }
                })

                funcionExterna()
            } else {
                // Autenticación fallida
            }
        }


        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoSpot)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }





    private fun performSearch(query: String) {
        println("Texto buscado: $query")
        buscarSong.searchSong(T1, object : SongDataCallback {
            override fun onSongDataReceived(cancion: SongData) {
                songData.imagen = cancion.imagen
                songData.artista = cancion.artista
                songData.cancion = cancion.cancion
                songData.key = cancion.key
                songData.bpm = cancion.bpm
                songData.popularidad = cancion.popularidad
            }

            override fun onError(message: String) {
                println("$message ERROR en SPOTY RQUEST")
            }
        }, query)
    }

    override fun onAuthResult(requestCode: Int, resultCode: Int, data: Intent?) {
        spotifyAuthenticator.setAuthResultListener(this)
        spotifyAuthenticator.handleAuthResponse(requestCode, resultCode, data)

        // Verificar si la autenticación con Spotify fue exitosa
        if (resultCode == RESULT_OK) {
            Log.d("SpotifyRequest", "Autenticación exitosa con Spotify")

            // Verificar si la autenticación con Google también fue exitosa
            val uri = data?.data
            if (uri != null && uri.toString() == GOOGLE_REDIRECT_URI) {
                // Autenticación exitosa con el URI de redireccionamiento de Google
                Log.d("SpotifyRequest", "Autenticación exitosa con el URI de redireccionamiento de Google")
                // Realizar acciones adicionales después de la autenticación exitosa con Google
                // ...
            } else {
                // Autenticación fallida con el URI de redireccionamiento de Google
                Log.d("SpotifyRequest", "Autenticación fallida con el URI de redireccionamiento de Google")
                // Mostrar mensaje de error o realizar acciones adicionales según sea necesario
                // ...
            }
        } else {
            Log.d("SpotifyRequest", "Autenticación fallida con el URI de redireccionamiento de Google")
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

   /* override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Server Finished", Toast.LENGTH_SHORT).show()
        server.stopServer()
    }*/
    override fun onPause() {
        super.onPause()
        // Limpia las credenciales almacenadas aquí
        val sharedPref = this.getSharedPreferences("SpotifyData", Context.MODE_PRIVATE)
        Log.d("MyApp", "onPause() called")
        // Limpia las credenciales almacenadas aquí
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.remove("Token")
        sharedPrefEditor.apply()
        Log.d("MyApp", "Credenciales eliminadas")
    }
    private fun funcionExterna() {
        SpotifyAppRemote.connect(
            applicationContext,
            ConnectionParams.Builder(CLIENT_ID)
                .setRedirectUri(GOOGLE_REDIRECT_URI)
                .showAuthView(true)
                .build(),
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    this@SpotifyRequest.spotifyAppRemote = spotifyAppRemote
                    Log.d("MainActivity", "Connected! Yay!")
                    // Ahora puedes interactuar con App Remote
                    connected()
                }

                override fun onFailure(throwable: Throwable) {
                    if (throwable is SpotifyRemoteServiceException) {
                        // Service died. Act accordingly.
                    } else if (throwable is CancellationException) {
                        // Connector/connection canceled.
                    }
                }
            }
        )
    }

    private fun connected() {
        // Then we will write some more code here.
    }

    private fun traerToken(): String {
        val sharedPref = this.getSharedPreferences("SpotifyData", Context.MODE_PRIVATE)
        return sharedPref.getString("Token", "") ?: ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        spotifyAuthenticator.onActivityResult(requestCode, resultCode, data)

        spotifyAuthenticator.setAuthResultListener(this)

       // spotifyAuthenticator.handleAuthResponse(requestCode, resultCode, data)

    }
}
package com.example.muweiz.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.muweiz.BuildConfig
import com.example.muweiz.R
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.resultadoSpotify
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import okhttp3.*
import com.android.volley.Request
import com.android.volley.Response
import com.example.muweiz.data.ServidorLocal.ServidorLocal
import com.example.muweiz.data.extention.toast
import com.example.muweiz.data.network.*
import com.example.muweiz.databinding.ActivitySpotifyRequestBinding
import com.spotify.android.appremote.api.error.SpotifyRemoteServiceException
import net.openid.appauth.*
import java.io.IOException
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


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySpotifyRequestBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnBuscar = binding.buscador
        songData = SongData(R.drawable.piano, "SRV", "Pride and Joy", "Eb Major", "130", "77")
        server = ServidorLocal("192.168.0.9", 8080, this)
        server.startServer()

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


    override fun onAuthResult(requestCode: Int, resultCode: Int, data: Intent?) {
        spotifyAuthenticator.handleAuthResponse(requestCode, resultCode, data)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("PAPAnata", "ENTRO AL ACTIVITY RESULT: $requestCode , $resultCode, $data")
        spotifyAuthenticator.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Server Finished", Toast.LENGTH_SHORT).show()
        server.stopServer()
    }

    private fun funcionExterna() {
        SpotifyAppRemote.connect(
            applicationContext,
            ConnectionParams.Builder(CLIENT_ID)
                .setRedirectUri(Uri.parse(redirectUri).toString())
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
}
package com.example.muweiz.ui.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.muweiz.BuildConfig
import com.example.muweiz.R
import com.example.muweiz.data.network.SpotifyApi
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.resultadoSpotify
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import okhttp3.*
import com.android.volley.Request
import com.android.volley.Response
import com.example.muweiz.data.network.BusquedaSpotify
import com.example.muweiz.data.network.SongData
import com.example.muweiz.data.network.SongDataCallback
import net.openid.appauth.*
import java.io.IOException

class SpotifyRequest : AppCompatActivity() {

    private val CLIENT_ID = BuildConfig.API_C
    private val CLIENT_ID_S = BuildConfig.API_CS
    private val requestCode = 1337
    private val redirectUri = "http://192.168.0.12:8888/callback"
    private val scope = "user-read-private user-library-read user-read-email user-read-private"
    private val authorizationEndpoint = "https://accounts.spotify.com/authorize"
    private lateinit var authService: AuthorizationService
    private val tokenEndpoint = "https://accounts.spotify.com/api/token"
    private lateinit var btnBuscar: SearchView
    private lateinit var songData: SongData
    private lateinit var buscarSong: BusquedaSpotify
    private lateinit var T1: String
    private lateinit var TR: String
    private lateinit var spotifyAppRemote: SpotifyAppRemote


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotify_request)

        btnBuscar = findViewById(R.id.buscador)
        songData= SongData(R.drawable.piano, "SRV", "Pride and Joy", "Eb Major", "130", "77")



        SpotifyAppRemote.connect(
            applicationContext,
            ConnectionParams.Builder(CLIENT_ID)
                .setRedirectUri(Uri.parse(redirectUri).toString())
                .showAuthView(true)
                .build(),
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    this@SpotifyRequest.spotifyAppRemote = spotifyAppRemote

                }
                override fun onFailure(t: Throwable) {
                    println(t)
                }
            }
        )

        val config = AuthorizationServiceConfiguration(
            Uri.parse(authorizationEndpoint),
            Uri.parse(tokenEndpoint)
        )

        val authRequest = AuthorizationRequest.Builder(
            config,
            CLIENT_ID,
            ResponseTypeValues.CODE,
            Uri.parse(redirectUri)
        )
            .setScope(scope)
            .build()

        authService = AuthorizationService(this)
        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        startActivityForResult(authIntent, 0)

        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoSpot)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }


        btnBuscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Acciones que se hacen cuando el texto se va modificando
                //de momento no usable.
                return false
            }
        })

        funcionExterna()
    }
    private fun performSearch(query: String) {

        println("Texto buscado: $query")
        buscarSong.searchSong(T1, object : SongDataCallback {
            override fun onSongDataReceived(cancion: SongData) {
                songData.imagen = cancion.imagen
                songData.artista  = cancion.artista
                songData.cancion = cancion.cancion
                songData.key = cancion.key
                songData.bpm = cancion.bpm
                songData.popularidad == cancion.popularidad
            }

            override fun onError(message: String) {
                println(message)
            }
        }, query)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)

            if (resp != null) {
                // Autorización exitosa, ahora se puede intercambiar el authorization code por un token de acceso
                exchangeAuthorizationCodeForAccessToken(resp.authorizationCode)
            } else if (ex != null) {
                // Ocurrió un error durante la autorización
                Log.e("SpotifyAuthorization", "Error during authorization: $ex")
            }

            finish()
        }
    }

    private fun exchangeAuthorizationCodeForAccessToken(authorizationCode: String?) {
        val tokenRequest = TokenRequest.Builder(
            AuthorizationServiceConfiguration(Uri.parse(authorizationEndpoint), Uri.parse(tokenEndpoint)),
            CLIENT_ID
        )
            .setAuthorizationCode(authorizationCode)
            .setRedirectUri(Uri.parse(redirectUri))
            .build()

        authService.performTokenRequest(tokenRequest) { response, ex ->
            if (response != null) {
                val accessToken = response.accessToken
                val refreshToken = response.refreshToken
                if (accessToken != null) {
                    T1 = accessToken
                }
                if (refreshToken != null) {
                    TR = refreshToken
                }
                // Aquí tienes el token de acceso y el token de actualización para realizar solicitudes a la API de Spotify
                // Puedes guardar los tokens y continuar con tu lógica de la aplicación
                Log.d("SpotifyAuthorization", "Access Token: $accessToken")
                Log.d("SpotifyAuthorization", "Refresh Token: $refreshToken")
            } else {
                Log.e("SpotifyAuthorization", "Error during token exchange: $ex")
            }
        }
    }


    override fun onBackPressed() {
        //onStop()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    fun funcionExterna(){

        val fragment = resultadoSpotify.newInstance(
            imagen = songData.imagen,
            artista = songData.artista,
            cancion = songData.cancion,
            key =  songData.key,
            bpm = songData.bpm,
            popularidad = songData.popularidad
        )
        supportFragmentManager.commit {
            replace(R.id.frameSpotifyResults, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")

        }

    }
}

//Para el envio de la info de la cancion
/*supportFragmentManager.commit {
    val bundle = Bundle()
    bundle.putInt("imagen", R.drawable.piano)
    bundle.putString("artista", "Stevie Ray Vaughan")
    bundle.putString("cancion", "Pride and Joy")
    bundle.putString("key", "Eb Major")
    bundle.putString("bpm", "120")
    bundle.putString("popularidad", "77")
    val fragment = resultadoSpotify()
    fragment.arguments = bundle
    replace(R.id.frameSpotifyResults, fragment)
    setReorderingAllowed(true)
    addToBackStack("replacement")
}*/
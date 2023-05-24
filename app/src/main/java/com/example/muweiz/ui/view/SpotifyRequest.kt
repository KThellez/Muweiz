package com.example.muweiz.ui.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.BuildConfig
import com.example.muweiz.R
import com.example.muweiz.data.network.SpotifyApi
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.resultadoSpotify
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.SpotifyAppRemote
import okhttp3.*
import java.io.IOException

class SpotifyRequest : AppCompatActivity() {

    private val CLIENT_ID = BuildConfig.API_C
    private val CLIENT_ID_S = BuildConfig.API_CS
    private val redirectUri = "http://192.168.0.12:8888/callback"
    private val scope = "user-read-private user-library-read"
    private lateinit var spotifyAppRemote: SpotifyAppRemote
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotify_request)


        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoSpot)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //Para el envio de la info de la cancion
        supportFragmentManager.commit {
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
        }


        //SPOTITRUCHO


        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()

        val authorizationUrl = "https://accounts.spotify.com/authorize" +
                "?client_id=$CLIENT_ID" +
                "&response_type=code" +
                "&redirect_uri=$redirectUri" +
                "&scope=$scope"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authorizationUrl))
        startActivity(intent)

        val buscar = findViewById<SearchView>(R.id.buscador)

        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //llamar a la función de realizar solicitud de búsqueda en  SpotifyApi
                val accessToken = "your_access_token" // Obtén el token de acceso necesario
                val spotifyApi = SpotifyApi()
                val response = spotifyApi.realizarSolicitudDeBusqueda(query, accessToken)

                // Aquí puedes procesar la respuesta de la solicitud y actualizar tu interfaz de usuario con los resultados de la búsqueda
                println("LA RESPUESTA DE LA BUSQUEDA EH: \n $response")

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Manejar cambios en el texto del SearchView, si es necesario
                return false
            }
        })


    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.let { uri ->
            if (uri.toString().startsWith(redirectUri)) {
                val code = uri.getQueryParameter("code")
                if (code != null) {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("https://accounts.spotify.com/api/token")
                        .post(
                            FormBody.Builder()
                                .add("grant_type", "authorization_code")
                                .add("code", code)
                                .add("redirect_uri", redirectUri)
                                .add("client_id", CLIENT_ID)
                                .add("client_secret", "TU_CLIENT_SECRET") // Reemplaza con tu propio Client Secret de Spotify
                                .build()
                        )
                        .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            // Maneja el error de la solicitud
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val responseData = response.body?.string()
                            // Procesa la respuesta de la solicitud
                            // Aquí obtienes el token de acceso
                        }
                    })
                } else {
                    // Maneja el error de autorización
                }
            }
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    /*
    override fun onDestroy() {
        super.onDestroy()
        //SpotifyAppRemote.disconnect(spotifyAppRemote)
    }*/
}
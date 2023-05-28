package com.example.muweiz.data.network

import android.util.Log
import androidx.fragment.app.commit
import com.example.muweiz.R
import com.example.muweiz.databinding.FragmentResultadoSpotifyBinding
import com.example.muweiz.ui.viewModel.resultadoSpotify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.spotify.com/"

class BusquedaSpotify {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val spotifyApiService: ServicioApiSpotify = retrofit.create(ServicioApiSpotify::class.java)
    private lateinit var songData: SongData
    // Llamada a la búsqueda de la canción
    fun searchSong(accessToken: String, callback: SongDataCallback, titulo: String) : SongData?{
        val call = spotifyApiService.searchTrack("Bearer $accessToken", "Still Got the Blues", "track")
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>){
                if (response.isSuccessful) {
                    val tracks = response.body()?.tracks?.items
                    if (!tracks.isNullOrEmpty()) {
                        val firstTrack = tracks[0]
                        val trackName = firstTrack.name
                        val artistName = firstTrack.artists.joinToString(", ") { it.name }
                        val trackUri = firstTrack.uri
                        val trackId = firstTrack.id
                        // Realiza una solicitud para obtener los detalles de la canción
                        val trackDetailsCall = spotifyApiService.getTrackDetails("Bearer $accessToken", trackId)
                        trackDetailsCall.enqueue(object : Callback<TrackDetailsResponse> {
                            override fun onResponse(call: Call<TrackDetailsResponse>, response: Response<TrackDetailsResponse>) {
                                if (response.isSuccessful) {
                                    val trackDetails = response.body()
                                    if (trackDetails != null) {
                                        val key = trackDetails.key
                                        val bpm = trackDetails.bpm
                                        val popularity = trackDetails.popularity

                                        // Actualiza los datos adicionales en el objeto SongData
                                        songData = SongData(
                                            imagen = R.drawable.piano,
                                            artista = artistName,
                                            cancion = trackName,
                                            key = key,
                                            bpm = bpm,
                                            popularidad = popularity
                                        )

                                        // Notifica al callback con los datos actualizados
                                        callback.onSongDataReceived(songData)
                                    }
                                } else {
                                    callback.onError("Error en la llamada: ${response.code()}")
                                    Log.e("SpotifySearch", "Error en la llamada: ${response.code()}")
                                }
                            }
                            override fun onFailure(call: Call<TrackDetailsResponse>, t: Throwable) {
                                callback.onError("Error en la llamada: ${t.message}")
                                Log.e("YourClass", "Error en la llamada: ${t.message}")
                            }
                        })
                        callback.onSongDataReceived(songData)
                        Log.d("SpotifySearch", "Track Name is: $trackName")
                        Log.d("SpotifySearch", "Artist Name: $artistName")
                        Log.d("SpotifySearch", "Track URI: $trackUri")
                    } else {
                        callback.onError("No se encontraron canciones")
                        Log.d("SpotifySearch", "No se encontraron canciones")
                    }
                } else {
                    callback.onError("Error en la llamada: ${response.code()}")
                    Log.e("SpotifySearch", "Error en la llamada: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback.onError("Error en la llamada: ${t.message}")
                Log.e("YourClass", "Error en la llamada: ${t.message}")
            }
        })
        if (::songData.isInitialized) {return songData}
        else {return null}
    }
}
/*
                        // Asignar los valores a las variables del fragmento
                        fragment.imagen = R.drawable.piano  // Asigna la imagen correspondiente
                        fragment.artista = artistName
                        fragment.cancion = trackName
                        fragment.key = "Eb Major"  // Asigna la clave de la canción
                        fragment.bpm = "120"  // Asigna el tempo de la canción
                        fragment.popularidad = "77"  // Asigna la popularidad de la canción
*/
package com.example.muweiz.data.network

import android.net.Uri.Builder
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.SpotifyAppRemote
import okhttp3.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException
import java.net.URLEncoder

class SpotifyApi {
    private val baseUrl = "https://api.spotify.com/v1"

    fun realizarSolicitudDeBusqueda(query: String, accessToken: String): String {
        val type = "album,artist,track"
        val encodedQuery = encodeURIComponent(query)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$baseUrl/search?q=$encodedQuery&type=$type")
            .header("Authorization", "Bearer $accessToken")
            .build()

        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }

    // Función auxiliar para codificar los parámetros de la URL
    private fun encodeURIComponent(input: String): String {
        return URLEncoder.encode(input, "UTF-8")
    }
}
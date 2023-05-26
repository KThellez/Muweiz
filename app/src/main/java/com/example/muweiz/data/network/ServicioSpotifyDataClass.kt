package com.example.muweiz.data.network

import com.google.gson.annotations.SerializedName

data class SearchResponse(val tracks: TracksResponse)

data class TracksResponse(val items: List<TrackItem>)

data class TrackItem(val id: String, val name: String, val artists: List<Artist>, val uri: String)

data class Artist(val name: String)

data class SongData(
    var imagen: Int,
    var artista: String,
    var cancion: String,
    var key: String,
    var bpm: String,
    var popularidad: String
)
data class TrackDetailsResponse(
    @SerializedName("key") val key: String,
    @SerializedName("tempo") val bpm: String,
    @SerializedName("popularity") val popularity: String
)
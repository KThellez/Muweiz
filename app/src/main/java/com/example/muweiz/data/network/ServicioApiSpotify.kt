package com.example.muweiz.data.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicioApiSpotify {
    @GET("v1/search")
    fun searchTrack(
        @Header("Authorization") authorization: String,
        @Query("q") query: String,
        @Query("type") type: String
    ): Call<SearchResponse>
    @GET("v1/tracks/{id}")
    fun getTrackDetails(
        @Header("Authorization") authorization: String,
        @Path("id") trackId: String
    ): Call<TrackDetailsResponse>
}

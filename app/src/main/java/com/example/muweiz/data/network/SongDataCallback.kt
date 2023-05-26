package com.example.muweiz.data.network

interface SongDataCallback {
    fun onSongDataReceived(songData: SongData)
    fun onError(message: String)
}
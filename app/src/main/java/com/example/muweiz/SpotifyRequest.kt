package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class SpotifyRequest : AppCompatActivity() {
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
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
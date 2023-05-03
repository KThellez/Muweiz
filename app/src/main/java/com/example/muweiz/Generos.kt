package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Generos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generos)

        val logo = findViewById<FrameLayout>(R.id.frameLogoGeneros);

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoGeneros)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Generos")
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloGeneros, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Clasico")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameClasico, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Blues")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameBlues, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Góspel")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameGospel, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Soul")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameSoul, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Jazz")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameJazz, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Rock")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameRock, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Funk")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameFunk, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        //APLICANDO LOS LISTENERS para cada BOTON
        logo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
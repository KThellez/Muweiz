package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Teoria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teoria)

        val logo = findViewById<FrameLayout>(R.id.frameLogoTeoria);

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTeoria)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Teoría")
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloTeoria, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Intervalos")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameIntervalos, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Armonía Básica")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameArmoniaI, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Escalas y Modos")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameEscalaModo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Cadencia II-V-I")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameCadencia, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Análisis Armónico")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameAnalisisArmonico, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "II-V-I Voicings")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameVoicings251, fragment)
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
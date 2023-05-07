package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Ejercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)

        val logo = findViewById<FrameLayout>(R.id.frameLogoEjercicios);

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoEjercicios)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Ejercicios")
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloEjercicios, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Auditivo")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameAuditivo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Ritmo")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameRitmo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Lectura")
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameLectura, fragment)
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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

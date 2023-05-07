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
            bundle.putString("texto", getString(R.string.teoria))
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloTeoria, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.intervalos))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameIntervalos, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.armonia_basica))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameArmoniaI, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.escalas_modos))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameEscalaModo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.cad_IIVI))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameCadencia, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.analisis_armonico))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameAnalisisArmonico, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.voicings))
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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
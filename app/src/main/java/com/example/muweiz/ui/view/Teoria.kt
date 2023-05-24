package com.example.muweiz.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.R
import com.example.muweiz.ui.viewModel.titulo
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.vista_documentos

class Teoria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teoria)

        //USAR los FRAMELAYOUT para que tengan listeners!
        val btnIntervalos = findViewById<FrameLayout>(R.id.frameIntervalos);
        val logo = findViewById<FrameLayout>(R.id.frameLogoTeoria);

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTeoria)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //se iinicia la vista principal
        iniciarFragments()

        //APLICANDO LOS LISTENERS para cada BOTON
        logo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        //Intervalos
        btnIntervalos.setOnClickListener{
            val intent = Intent(this, vista_documentos::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun iniciarFragments(){
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
    }
    private fun vistaIntervalos(){
        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.def_intervalos))
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloTeoria, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}
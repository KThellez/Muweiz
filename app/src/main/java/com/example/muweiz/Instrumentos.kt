package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Instrumentos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrumentos)
        val logo = findViewById<FrameLayout>(R.id.frameLogoInstrumentos);

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoInstrumentos)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", "Instrumentos")
            val fragment = titulo()
            fragment.arguments = bundle

            replace(R.id.frameTituloInstrumentos, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.guitar)
            bundle.putString("texto", "Bajo")
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle

            replace(R.id.frameBajo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.guitar)
            bundle.putString("texto", "Guitarra")
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle

            replace(R.id.frameGuitarra, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.piano)
            bundle.putString("texto", "Piano")
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle

            replace(R.id.framePiano, fragment)
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
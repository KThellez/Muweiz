package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //USAR los FRAMELAYOUT para que tengan listeners!
        val btnMetronomo = findViewById<FrameLayout>(R.id.frameMetronomo);
        val btnAfinador = findViewById<FrameLayout>(R.id.frameAfinador);
        val btnTeoria = findViewById<FrameLayout>(R.id.frameTeoria);
        val btnEjercicios = findViewById<FrameLayout>(R.id.frameEjercicios);
        val btnGuitarra = findViewById<FrameLayout>(R.id.frameGuitar);
        val btnPiano = findViewById<FrameLayout>(R.id.framePiano);
        val btnGeneros = findViewById<FrameLayout>(R.id.frameGenero);


    //Añadir por defecto el fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogo)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    //De aqui en adelante se añade el fragment pero con un texto e imagen escogidas.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.metronomo)
            bundle.putString("texto", "Metrónomo")
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameMetronomo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.tuner)
            bundle.putString("texto", "Afinador")
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameAfinador, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.teoria)
            bundle.putString("texto", "Teoría")
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameTeoria, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.cronometro)
            bundle.putString("texto", "Ejercicios")
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle


            replace(R.id.frameEjercicios, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    //Botones inst y genero....

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.guitar)
            bundle.putString("texto", "Guitarra")
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameGuitar, fragment)
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
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.genero)
            bundle.putString("texto", "Géneros")
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameGenero, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    //APLICANDO LOS LISTENERS para cada BOTON

        btnMetronomo.setOnClickListener{

            val intent = Intent(this, Metronomo::class.java)
            startActivity(intent)
            finish()
        }
        btnAfinador.setOnClickListener{

            val intent = Intent(this, Tuner::class.java)
            startActivity(intent)
            finish()
        }

    }
    
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}





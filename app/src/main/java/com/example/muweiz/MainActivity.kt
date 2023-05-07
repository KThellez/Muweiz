package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //USAR los FRAMELAYOUT para que tengan listeners!
        val logo = findViewById<FrameLayout>(R.id.frameLogo);
        val btnMetronomo = findViewById<FrameLayout>(R.id.frameMetronomo);
        val btnAfinador = findViewById<FrameLayout>(R.id.frameAfinador);
        val btnTeoria = findViewById<FrameLayout>(R.id.frameTeoria);
        val btnEjercicios = findViewById<FrameLayout>(R.id.frameEjercicios);
        val btnInstrumentos = findViewById<FrameLayout>(R.id.frameInstrumentos);
        val btnBuscarCancion = findViewById<FrameLayout>(R.id.frameProximo);
        val btnGeneros = findViewById<FrameLayout>(R.id.frameGenero);

    //Analitycs Event to google
        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundelAnalitics = Bundle()
        bundelAnalitics.putString("mesage", "Integracion de Firebase Completa")
        analitycs.logEvent("InitScreen", bundelAnalitics)

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
            bundle.putString("texto", getString(R.string.metronomo))
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameMetronomo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.tuner)
            bundle.putString("texto", getString(R.string.afinador))
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameAfinador, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.teoria)
            bundle.putString("texto", getString(R.string.teoria))
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle

            replace(R.id.frameTeoria, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.cronometro)
            bundle.putString("texto", getString(R.string.ejercicios))
            val fragment = btnPrincipalesMenu()
            fragment.arguments = bundle


            replace(R.id.frameEjercicios, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    //Botones inst y genero....

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.piano)
            bundle.putString("texto", getString(R.string.instrumentos))
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameInstrumentos, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.searchsong)
            bundle.putString("texto", getString(R.string.buscar_cancion))
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameProximo, fragment)
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
        logo.setOnClickListener{
            Toast.makeText(applicationContext, getString(R.string.estas_home), Toast.LENGTH_SHORT).show()
        }

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
        btnTeoria.setOnClickListener{

            val intent = Intent(this, Teoria::class.java)
            startActivity(intent)
            finish()
        }
        btnEjercicios.setOnClickListener{

            val intent = Intent(this, Ejercicios::class.java)
            startActivity(intent)
            finish()
        }
        btnGeneros.setOnClickListener{
            val intent = Intent(this, Generos::class.java)
            startActivity(intent)
            finish()
        }
        btnInstrumentos.setOnClickListener{
            val intent = Intent(this, Instrumentos::class.java)
            startActivity(intent)
            finish()
        }

        btnBuscarCancion.setOnClickListener{
            val intent = Intent(this, SpotifyRequest::class.java)
            startActivity(intent)
            finish()
        }


    }
    
    override fun onBackPressed() {
        /*val builder = AlertDialog.Builder(this, R.style.MiAlertDialogStyle)
        builder.setTitle("Confirmar salida")
        builder.setMessage("¿Estás seguro que quieres salir?")
        builder.setPositiveButton("Si") { dialog, which ->
            finishAffinity()
        }
        builder.setNegativeButton("No", null)
        builder.show()*/

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirmar_salida))
            .setMessage(getString(R.string.pregunta_confirmar_salida))
            .setPositiveButton(getString(R.string.si)) { _, _ ->
                finishAffinity()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

}





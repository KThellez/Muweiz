package com.example.muweiz.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.R
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.metronomoPrincipal

class Metronomo : AppCompatActivity() {
    private lateinit var fragmentoPrincipalMetronomo: metronomoPrincipal

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metronomo)


        val logo = findViewById<FrameLayout>(R.id.frameLogoMetronomo);
        val compas = findViewById<TextView>(R.id.txtCompas); //Compas
        val tempoNombre = findViewById<TextView>(R.id.txtTemCla); //allegro
        val imgFiguraRithm = findViewById<ImageView>(R.id.imgSubdiv); //Imagen Figura Ritmica de Subdivision
        val bpm = findViewById<TextView>(R.id.textBPMnum); // Numero Que indica los BPM
        val btnIniciar = findViewById<TextView>(R.id.btnIniciarMetronomo); // Boton Iniciar Metronomo
        val btnTapTempo = findViewById<TextView>(R.id.btnTapMetronomo); //Boton Tap para tempo
        val btnTempo = findViewById<FrameLayout>(R.id.frameTempo); //Boton que abre el frame de ajustes de metronomo (compaz, tempo, subdivision)
        val btnConfiguracion = findViewById<FrameLayout>(R.id.frameConfig); // Boton de las configuraciones de mertonomo (Sonido)

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoMetronomo)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            replace<metronomoPrincipal>(R.id.frameMetronomo)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.genero)
            bundle.putString("texto", getString(R.string.tempo))
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameTempo, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.config)
            bundle.putString("texto", getString(R.string.configuracion))
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle


            replace(R.id.frameConfig, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")

        }
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoMetronomo)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    //LOGICA METRONOMO

    //LISTENERS
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
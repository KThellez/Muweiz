package com.example.muweiz.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ekn.gruzer.gaugelibrary.HalfGauge
import com.ekn.gruzer.gaugelibrary.Range
import com.example.muweiz.R
import com.example.muweiz.ui.viewModel.tunerPrincipal
import com.example.muweiz.ui.viewModel.logoAndTittle

class Tuner : AppCompatActivity() {
    private lateinit var  ran1 : com.ekn.gruzer.gaugelibrary.Range
    private lateinit var  ran2 : com.ekn.gruzer.gaugelibrary.Range
    private lateinit var  ran3 : com.ekn.gruzer.gaugelibrary.Range
    private lateinit var  ran4 : com.ekn.gruzer.gaugelibrary.Range
    private lateinit var  ran5 : com.ekn.gruzer.gaugelibrary.Range

    //;

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuner)

        val logo = findViewById<FrameLayout>(R.id.frameLogoTuner);

        var medidor = findViewById<HalfGauge>(R.id.medidor_tuner);

        if (medidor != null) {
            medidor.apply {

                minValue = -50.0
                maxValue = 50.0
                value = 0.0
                addRange(ran1)
                addRange(ran2)
                addRange(ran3)
                addRange(ran4)
                addRange(ran5)
            }
            ran1 = Range().apply {
                from = -50.0
                to = -20.1
                color = Color.parseColor("#C31A1A")
            }
            ran2 = Range().apply {
                from = -20.0
                to = -10.1
                color = Color.parseColor("#C36B1A")

            }
            ran3 = Range().apply {
                from = -10.0
                to = 10.0
                color = Color.parseColor("#1AC321")
            }
            ran4 = Range().apply {
                from = 10.1
                to = 20.0
                color = Color.parseColor("#C36B1A")
            }
            ran5 = Range().apply {
                from = 20.1
                to = 50.0
                color = Color.parseColor("#C31A1A")
            }
        }

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTuner)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        supportFragmentManager.commit {
            replace<tunerPrincipal>(R.id.frameTuner)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        //Abre el fragment y envia parametros.

        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.tuner)
            bundle.putString("texto", getString(R.string.afinacion_tipo))
            val fragment = btnImgTxtHorizontal()
            fragment.arguments = bundle

            replace(R.id.frameAfinacion, fragment)
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
            replace<logoAndTittle>(R.id.frameLogoTuner)
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
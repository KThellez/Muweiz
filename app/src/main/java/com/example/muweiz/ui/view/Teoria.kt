package com.example.muweiz.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.R
import com.example.muweiz.data.ContentItemDocumentos
import com.example.muweiz.data.ContentType
import com.example.muweiz.databinding.ActivityTeoriaBinding
import com.example.muweiz.ui.viewModel.titulo
import com.example.muweiz.ui.viewModel.logoAndTittle
import com.example.muweiz.ui.viewModel.vista_documentos

class Teoria : AppCompatActivity() {
    private lateinit var binding: ActivityTeoriaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTeoria)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        //se inicia la vista principal
        iniciarFragments()

        //APLICANDO LOS LISTENERS para cada BOTON
        binding.frameLogoTeoria.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        //Intervalos
        binding.frameConceptos.setOnClickListener {
            vistaBasics()
        }
        binding.frameIntervalos .setOnClickListener{
            val intent = Intent(this, vista_documentos::class.java)
            startActivity(intent)
            finish()
        }
        binding.frameArmoniaI.setOnClickListener { startActivity(Intent(this, WorkInPogress::class.java)) }
        binding.frameEscalaModo.setOnClickListener {startActivity(Intent(this, WorkInPogress::class.java))  }
        binding.frameCadencia.setOnClickListener { startActivity(Intent(this, WorkInPogress::class.java)) }
        binding.frameAnalisisArmonico.setOnClickListener { startActivity(Intent(this, WorkInPogress::class.java)) }
        binding.frameVoicings251.setOnClickListener { startActivity(Intent(this, WorkInPogress::class.java)) }





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
            bundle.putString("texto", getString(R.string.conceptos))
            val fragment = btnTextOnly()
            fragment.arguments = bundle

            replace(R.id.frameConceptos, fragment)
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

    private fun vistaBasics(){
        val intent = Intent(this, vista_documentos::class.java)

        // Crear la lista de elementos de contenido a enviar
        val contentList = mutableListOf<ContentItemDocumentos>()
        contentList.add(ContentItemDocumentos(ContentType.H1, true, false, getString(R.string.h1_Conceptos_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, false, getString(R.string.p1_Conceptos_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P2, false, true, getString(R.string.p2_Conceptos_1), "#cccccc"))
        contentList.add(ContentItemDocumentos(ContentType.H2, true, false, getString(R.string.h2_Conceptos_1_2), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_2), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_2), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_2_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_2_1), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P2, false, true, getString(R.string.p2_Conceptos_1_2_2_1), "#cccccc"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_2_2), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_2_2), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P2, false, true, getString(R.string.p2_Conceptos_1_2_2_2), "#cccccc"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_2_3), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_2_3), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P2, false, true, getString(R.string.p2_Conceptos_1_2_2_3), "#cccccc"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_2_4), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_2_4), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_3), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_3), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.H3, false, false, getString(R.string.h3_Conceptos_1_2_4), "#ffffff"))
        contentList.add(ContentItemDocumentos(ContentType.P1, false, true, getString(R.string.p1_Conceptos_1_2_4), "#ffffff"))

        // Pasar la lista de elementos de contenido a la actividad vista_documentos
        intent.putExtra("content_list", ArrayList(contentList))

        startActivity(intent)
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
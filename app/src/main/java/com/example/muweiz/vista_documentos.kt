package com.example.muweiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class vista_documentos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_documentos)
    //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTeoria)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        vistaIntervalos()
    }
    override fun onBackPressed() {
        startActivity(Intent(this, Teoria::class.java))
        finish()
    }
    private fun vistaIntervalos(){
        //Abre el fragment y envia parametros.
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putString("texto", getString(R.string.def_intervalos))
            val fragment = onlyText()
            fragment.arguments = bundle

            replace(R.id.frameTextoPrincipal, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        supportFragmentManager.commit {
            val bundle = Bundle()
            bundle.putInt("imagen", R.drawable.intervalouno)
            val fragment = onlyImg()
            fragment.arguments = bundle

            replace(R.id.frameImgPrincipal, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}
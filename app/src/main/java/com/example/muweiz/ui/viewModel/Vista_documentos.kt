package com.example.muweiz.ui.viewModel

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.muweiz.R
import com.example.muweiz.data.ContentItemDocumentos
import com.example.muweiz.data.ContentType
import com.example.muweiz.databinding.ActivityVistaDocumentosBinding
import com.example.muweiz.ui.view.Teoria

class vista_documentos : AppCompatActivity() {
    private lateinit var binding: ActivityVistaDocumentosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaDocumentosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Abre el Fragment
        supportFragmentManager.commit {
            replace<logoAndTittle>(R.id.frameLogoTeoria)
            setReorderingAllowed(true)
            addToBackStack("replacement")

            // Obtener la lista de elementos de contenido enviados a través del Intent
            val contentList =intent.getSerializableExtra("content_list") as? ArrayList<ContentItemDocumentos>


            // Llenar la vista con el contenido
            fillContentView(contentList)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, Teoria::class.java))
        finish()
    }


private fun fillContentView(contentList: List<ContentItemDocumentos>?) {
    if (contentList != null) {
        Log.d("VistaDocumentos", "Tamaño de contentList: ${contentList.size}")

        if (contentList.isNotEmpty()) {
            for (item in contentList) {
                if (!TextUtils.isEmpty(item.text)) { // Verificar que el texto no esté en blanco
                    when (item.type) {
                        ContentType.H1 -> H1(item.bold, item.italic, item.text, item.color)
                        ContentType.H2 -> H2(item.bold, item.italic, item.text, item.color)
                        ContentType.H3 -> H3(item.bold, item.italic, item.text, item.color)
                        ContentType.P1 -> p1(item.bold, item.italic, item.text, item.color)
                        ContentType.P2 -> p2(item.bold, item.italic, item.text, item.color)
                    }
                } else {
                    Log.d("VistaDocumentos", "El texto en ContentItemDocumentos está en blanco.")
                }
            }
        } else {
            Log.d("VistaDocumentos", "La lista contentList está vacía.")
        }
    } else {
        Log.d("VistaDocumentos", "La lista contentList es nula.")
    }
}
    private fun H1(bold: Boolean, italic: Boolean, texto: String, color: String){
        val textView = TextView(this)
        textView.text = texto
        textView.textSize = 24f
        textView.setTextColor(Color.parseColor(color))

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        if (italic) {
            textView.setTypeface(null, Typeface.ITALIC)
        }

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        textView.layoutParams = layoutParams

        val container = binding.layoutContenedorInformacion
        container.addView(textView)

    }

    private fun H2(bold: Boolean, italic: Boolean, texto: String, color: String){
        val textView = TextView(this)
        textView.text = texto
        textView.textSize = 20f
        textView.setTextColor(Color.parseColor(color))

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        if (italic) {
            textView.setTypeface(null, Typeface.ITALIC)
        }

        val container = binding.layoutContenedorInformacion
        container.addView(textView)
    }
    private fun H3(bold: Boolean, italic: Boolean, texto: String, color: String){
        val textView = TextView(this)
        textView.text = texto
        textView.textSize = 18f
        textView.setTextColor(Color.parseColor(color))

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        if (italic) {
            textView.setTypeface(null, Typeface.ITALIC)
        }

        val container = binding.layoutContenedorInformacion
        container.addView(textView)
    }
    private fun p1(bold: Boolean, italic: Boolean, texto: String, color: String){
        val textView = TextView(this)
        textView.text = texto
        textView.textSize = 16f
        textView.setTextColor(Color.parseColor(color))

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        if (italic) {
            textView.setTypeface(null, Typeface.ITALIC)
        }


        val container = binding.layoutContenedorInformacion
        container.addView(textView)

    }
    private fun p2(bold: Boolean, italic: Boolean, texto: String, color: String){
        val textView = TextView(this)
        textView.text = texto
        textView.textSize = 14f
        textView.setTextColor(Color.parseColor(color))

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        if (italic) {
            textView.setTypeface(null, Typeface.ITALIC)
        }

        val container = binding.layoutContenedorInformacion
        container.addView(textView)
    }
    private fun imagen(){
        /*val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.mi_imagen)
        val container = findViewById<LinearLayout>(R.id.container)
        container.addView(imageView)*/
    }
}
package com.example.muweiz.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.muweiz.R



private const val ARG_IMAGEN = "imagen"
private const val ARG_TEXTO = "texto"
class btnPrincipalesMenu : Fragment() {

    private var imagen: Int? = null
    private var texto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagen = it.getInt(ARG_IMAGEN)
            texto = it.getString(ARG_TEXTO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val imagenResId = bundle?.getInt("imagen") ?: 0
        val texto = bundle?.getString("texto") ?: ""

        // Infla el layout del fragment
        val rootView = inflater.inflate(R.layout.fragment_btn_principales_menu, container, false)

        // Configura la imagen y el texto en tus vistas
        rootView.findViewById<ImageView>(R.id.imgBtnPrincipalMenu).setImageResource(imagenResId)
        rootView.findViewById<TextView>(R.id.txtBtnPrincipalMenu).text = texto

        return rootView

        // Accede al Bundle y obtén los datos
        //return inflater.inflate(R.layout.fragment_btn_principales_menu, container, false)
    }
    /*
        * val bundle = arguments
        val imagenResId = bundle?.getInt("imagen") ?: 0
        val texto = bundle?.getString("texto") ?: ""

        // Infla el layout del fragment
        val rootView = inflater.inflate(R.layout.fragment_btn_principales_menu, container, false)

        // Configura la imagen y el texto en tus vistas
        rootView.findViewById<ImageView>(R.id.imgBtnPrincipalMenu).setImageResource(imagenResId)
        rootView.findViewById<TextView>(R.id.txtBtnPrincipalMenu).text = texto

        return rootView
        * */
/*
    companion object {

        @JvmStatic
        fun newInstance(imagen: Int, texto: String) =
            btnPrincipalesMenu().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMAGEN, imagen)
                    putString(ARG_TEXTO, texto)

                }
            }
    }*/
}
package com.example.muweiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


private const val ARG_IMAGEN = "imagen"
private const val ARG_TEXTO = "texto"


class btnImgTxtHorizontal : Fragment() {

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
        val rootView = inflater.inflate(R.layout.fragment_btn_img_txt_horizontal, container, false)

        // Configura la imagen y el texto en tus vistas
        rootView.findViewById<ImageView>(R.id.imgBtnHorizontal).setImageResource(imagenResId)
        rootView.findViewById<TextView>(R.id.txtBtnHorizontal).text = texto
        return rootView
    }
}
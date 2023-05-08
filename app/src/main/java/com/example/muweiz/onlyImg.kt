package com.example.muweiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

private const val ARG_IMAGEN = "imagen"


class onlyImg : Fragment() {
    private var imagen: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagen = it.getInt(ARG_IMAGEN)

        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val imagenResId = bundle?.getInt("imagen") ?: 0
        // Infla el layout del fragment

        val rootView = inflater.inflate(R.layout.fragment_only_img, container, false)
        // Configura la imagen y el texto en tus vistas
        rootView.findViewById<ImageView>(R.id.imgOnly).setImageResource(imagenResId)

        return rootView
    }
    }
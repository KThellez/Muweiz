package com.example.muweiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_TEXTO = "texto"

class onlyText : Fragment() {
    private var texto: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            texto = it.getString(ARG_TEXTO)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_only_text, container, false)
        rootView.findViewById<TextView>(R.id.txtOnly).text = texto
        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance(texto: String) =
            btnTextOnly().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXTO, texto)
                }
            }
    }
}
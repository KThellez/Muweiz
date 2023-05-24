package com.example.muweiz.ui.viewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.muweiz.R
import com.example.muweiz.ui.view.btnTextOnly


private const val ARG_TEXTO = "texto"

class titulo : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_titulo, container, false)
        rootView.findViewById<TextView>(R.id.txtGenerico).text = texto
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
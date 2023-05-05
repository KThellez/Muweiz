package com.example.muweiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_IMAGEN = "imagen"
private const val ARG_ARTISTA = "artista"
private const val ARG_CANCION = "cancion"
private const val ARG_KEY= "key"
private const val ARG_BPM= "bpm"
private const val ARG_POPULARIDAD= "popularidad"


class resultadoSpotify : Fragment() {
    // TODO: Rename and change types of parameters
    private var imagen: Int? = null
    private var artista: String? = null
    private var cancion: String? = null
    private var key: String? = null
    private var bpm: String? = null
    private var popularidad: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagen = it.getInt(ARG_IMAGEN)
            artista = it.getString(ARG_ARTISTA)
            cancion = it.getString(ARG_CANCION)
            key = it.getString(ARG_KEY)
            bpm = it.getString(ARG_BPM)
            popularidad = it.getString(ARG_POPULARIDAD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val imagen = bundle?.getInt("imagen") ?: 0
        val artista = bundle?.getString("artista") ?: ""
        val cancion = bundle?.getString("cancion") ?: ""
        val key = bundle?.getString("key") ?: ""
        val bpm = bundle?.getString("bpm") ?: ""
        val popularidad = bundle?.getString("popularidad") ?: ""

        // Infla el layout del fragment
        val rootView = inflater.inflate(R.layout.fragment_resultado_spotify, container, false)

        // Configura la imagen y el texto en tus vistas
        rootView.findViewById<ImageView>(R.id.imgCancion).setImageResource(imagen)
        rootView.findViewById<TextView>(R.id.txtNombreArtista).text = artista
        rootView.findViewById<TextView>(R.id.txtNombreCancion).text = cancion
        rootView.findViewById<TextView>(R.id.txtKeySong).text = key
        rootView.findViewById<TextView>(R.id.txtBpmSong).text = bpm
        rootView.findViewById<TextView>(R.id.txtPopSong).text = popularidad
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment resultadoSpotify.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(imagen: Int, artista: String, cancion: String,key: String ,
                        bpm: String, popularidad: String) =
            resultadoSpotify().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMAGEN, imagen)
                    putString(ARG_ARTISTA, artista)
                    putString(ARG_CANCION, cancion)
                    putString(ARG_KEY, key)
                    putString(ARG_BPM, bpm)
                    putString(ARG_POPULARIDAD, popularidad)
                }
            }
    }
}
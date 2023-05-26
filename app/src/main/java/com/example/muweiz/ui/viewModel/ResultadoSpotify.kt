package com.example.muweiz.ui.viewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.muweiz.*
import com.example.muweiz.data.network.SongData
import com.example.muweiz.data.network.SongDataCallback
import com.example.muweiz.databinding.FragmentResultadoSpotifyBinding

private var imagen: Int? = null
private var artista: String? = null
private var cancion: String? = null
private var key: String? = null
private var bpm: String? = null
private var popularidad: String? = null

class resultadoSpotify : Fragment(), SongDataCallback {
    var imagen: Int? = null
    var artista: String? = null
    var cancion: String? = null
    var key: String? = null
    var bpm: String? = null
    var popularidad: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagen = it.getInt("imagen")
            artista = it.getString("artista")
            cancion = it.getString("cancion")
            key = it.getString("key")
            bpm = it.getString("bpm")
            popularidad = it.getString("popularidad")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actualizarInterfaz(view)
    }

    private fun actualizarInterfaz(view: View) {
        if (imagen != null && artista != null && cancion != null && key != null && bpm != null && popularidad != null) {
            imagen?.let { view.findViewById<ImageView>(R.id.imgCancion)?.setImageResource(it) }
            artista?.let { view.findViewById<TextView>(R.id.txtNombreArtista)?.text = it }
            cancion?.let { view.findViewById<TextView>(R.id.txtNombreCancion)?.text = it }
            key?.let { view.findViewById<TextView>(R.id.txtKeySong)?.text = it }
            bpm?.let { view.findViewById<TextView>(R.id.txtBpmSong)?.text = it }
            popularidad?.let { view.findViewById<TextView>(R.id.txtPopSong)?.text = it }
        }
    }
    override fun onSongDataReceived(songData: SongData) {
        val fragment = resultadoSpotify.newInstance(
            imagen = songData.imagen,
            artista = songData.artista,
            cancion = songData.cancion,
            key = songData.key,
            bpm = songData.bpm,
            popularidad = songData.popularidad
        )

        parentFragmentManager.commit {
            replace(R.id.frameSpotifyResults, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    override fun onError(message: String) {
        println("Error al traer la cancion al fragment!")
        println(message)
    }

    companion object {
        @JvmStatic
        fun newInstance(imagen: Int, artista: String, cancion: String, key: String, bpm: String, popularidad: String): resultadoSpotify {
            val fragment = resultadoSpotify()
            val args = Bundle().apply {
                putInt("imagen", imagen)
                putString("artista", artista)
                putString("cancion", cancion)
                putString("key", key)
                putString("bpm", bpm)
                putString("popularidad", popularidad)
            }
            fragment.arguments = args
            return fragment
        }
    }


}
/*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_resultado_spotify, container, false)
        rootView.findViewById<ImageView>(R.id.imgCancion).setImageResource(imagen ?: 0)
        rootView.findViewById<TextView>(R.id.txtNombreArtista).text = artista ?: ""
        rootView.findViewById<TextView>(R.id.txtNombreCancion).text = cancion ?: ""
        rootView.findViewById<TextView>(R.id.txtKeySong).text = key ?: ""
        rootView.findViewById<TextView>(R.id.txtBpmSong).text = bpm ?: ""
        rootView.findViewById<TextView>(R.id.txtPopSong).text = popularidad ?: ""
        return rootView
    }*/

/*
* fun actualizarInterfaz(view: View){
        if (imagen != null && artista != null && cancion != null && key != null && bpm != null && popularidad != null) {
            // Configurar la imagen y el texto en tus vistas
            view?.findViewById<ImageView>(R.id.imgCancion)?.setImageResource(imagen)
            view?.findViewById<TextView>(R.id.txtNombreArtista)?.text = artista
            view?.findViewById<TextView>(R.id.txtNombreCancion)?.text = cancion
            view?.findViewById<TextView>(R.id.txtKeySong)?.text = key
            view?.findViewById<TextView>(R.id.txtBpmSong)?.text = bpm
            view?.findViewById<TextView>(R.id.txtPopSong)?.text = popularidad
        }

    }
* */
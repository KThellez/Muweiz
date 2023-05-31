package com.example.muweiz.ui.viewModel.picasso

import android.util.Log
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class FirebaseImageLoader {
    private val storageReference = FirebaseStorage.getInstance().reference

    fun loadImage(imageName: String, imageView: ImageView, categoy: String) {
        val imageRef = storageReference.child("imagenes/$categoy/$imageName")

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Picasso.get().load(uri).into(imageView)
            Log.d("Load Image firebase", "Imagen cargada exitosamente: $imageName")
        }.addOnFailureListener { exception ->
            Log.d("Load Image firebase", "No se cargaron las imagenes")
        }
    }
}
package com.example.muweiz.data

import java.io.Serializable

class ContentItemDocumentos(
    val type: ContentType,
    val bold: Boolean,
    val italic: Boolean,
    val text: String,
    val color: String
) : Serializable
package com.example.muweiz.data.ServidorLocal



import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsSession
import com.example.muweiz.BuildConfig
import com.example.muweiz.data.extention.toast
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress
import com.example.muweiz.data.extention.toast
import com.example.muweiz.ui.view.SpotifyRequest


class ServidorLocal(private val ipAddress: String, private val port: Int, private val activity: Activity) {
    private val server = HttpServer.create(InetSocketAddress(ipAddress, port), 0)

    init {
        server.createContext("/callback") { exchange ->
            val redirectUri = BuildConfig.URI // Reemplaza con el URI de redireccionamiento registrado en Spotify Developer
            val response = "¡Redirección exitosa! Serás redirigido a la aplicación."
            exchange.responseHeaders.add("Content-Type", "text/html; charset=UTF-8")
            exchange.sendResponseHeaders(302, response.toByteArray().size.toLong()) // Cambia el código de estado a 302 para indicar redirección
            exchange.getResponseHeaders().add("Location", redirectUri)

            val outputStream: OutputStream = exchange.responseBody
            outputStream.write(response.toByteArray())
            outputStream.close()
        }
    }


    fun startServer() {
        server.start()
        println("Servidor iniciado en http://$ipAddress:$port")
    }

    fun stopServer() {
        server.stop(0)
        println("Servidor detenido")
    }
}



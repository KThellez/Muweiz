package com.example.muweiz.data.ServidorLocal



import android.app.Activity
import android.widget.Toast
import com.example.muweiz.data.extention.toast
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress
import com.example.muweiz.data.extention.toast

class ServidorLocal(private val ipAddress: String, private val port: Int) {
    private val server = HttpServer.create(InetSocketAddress(ipAddress, port), 0)

    init {
        server.createContext("/callback") { exchange ->
            val redirectUri = "http://$ipAddress:$port/callback"
            val response = "¡Redirección exitosa! Serás redirigido a la aplicación."

            exchange.responseHeaders.add("Content-Type", "text/html; charset=UTF-8") //Agregarle el protocolo Charset UTF-8
            exchange.sendResponseHeaders(302, response.toByteArray().size.toLong())
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



package com.ieseljust.pmdm.Model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket


class CommunicationManager {
    val SERVER_PORT = 9999

    var server: String? = null
    var listenPort: Int? = null

    /**
     * Método para enviar un mensaje al servidor y recibir la respuesta.
     */
    fun sendServer(msg: String): JSONObject {

            val socket = Socket()
            val socketADDR: InetSocketAddress =
                InetSocketAddress(server, SERVER_PORT)

            try {
                socket.connect(socketADDR)
                val `is` = socket.getInputStream()
                val os = socket.getOutputStream()
                val osw = OutputStreamWriter(os)
                val isr = InputStreamReader(`is`)
                val bReader = BufferedReader(isr)
                val pw = PrintWriter(osw)

                pw.println(msg)
                pw.flush()

                val line = bReader.readLine()
                val resposta = JSONObject(line)

                bReader.close()
                pw.close()
                return resposta

            } catch (ex: IOException) {
                println("La conexió no se ha pogut establir.")
                val noResposta = JSONObject()
                return noResposta
            }
        }
    }

    /**
     * Método para preparar el cliente para escuchar mensajes del servidor.
     */
    suspend fun prepareListener() {

        GlobalScope.launch(Dispatchers.IO) {
            val serverSocket = ServerSocket(listenPort!!)
            while (true) {
                val clientSocket = serverSocket.accept()
                GlobalScope.launch {
                    handleClientRequest(clientSocket)
                }
            }
        }
    }

    /**
     * Método para procesar las notificaciones recibidas del servidor.
     */
    suspend fun processNotification(s: String) {
        val json = JSONObject(s)
        val messageType = json.getString("type")

        when (messageType) {
            "message" -> {
                // Para la App en Android solo consideramos el tipo de mensaje "message"
                val nomUsuari = json.getString("nomUsuari")
                val mensaje = json.getString("mensaje")
                Message.add(nomUsuari, mensaje)
            }
            // Agregar otros casos según las necesidades
        }
    }

    /**
     * Método para manejar las peticiones de clientes.
     */
    private suspend fun handleClientRequest(clientSocket: Socket) {
        try {
            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val writer = BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream()))

            // Leer la línea recibida
            val request = reader.readLine()

            // Procesar la notificación
            processNotification(request)

            // Responder con un mensaje de confirmación
            val response = JSONObject().put("status", "ok").toString()
            writer.write(response)
            writer.newLine()
            writer.flush()

            // Cerrar streams y socket
            reader.close()
            writer.close()
            clientSocket.close()
        } catch (e: Exception) {
            // Manejar excepciones, si es necesario
        }
    }
}
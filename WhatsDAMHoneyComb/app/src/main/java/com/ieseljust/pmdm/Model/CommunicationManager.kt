package com.ieseljust.pmdm.Model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket


class CommunicationManager {
    val SERVER_PORT = 9999

    var server: String? = null
    var listenPort: Int? = null

    /**
     * Método para enviar un mensaje al servidor y recibir la respuesta.
     */
    suspend fun sendServer(msg: String): JSONObject {
        return withContext(Dispatchers.IO) {
            try {
                val socket = Socket(server, SERVER_PORT)
                val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                // Enviar mensaje al servidor
                writer.write(msg)
                writer.newLine()
                writer.flush()

                // Recibir respuesta del servidor
                val response = reader.readLine()

                // Cerrar streams y socket
                writer.close()
                reader.close()
                socket.close()

                // Transformar la respuesta a JSON y devolverla
                JSONObject(response)
            } catch (e: Exception) {
                // Manejar excepciones, por ejemplo, enviar un mensaje de error
                JSONObject().put("status", "error")
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
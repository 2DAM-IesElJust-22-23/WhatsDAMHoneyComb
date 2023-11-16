package com.ieseljust.pmdm.Model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Creació de la classe Messages.
 */
class Messages(
    var nomUsuari:String,
    var mensaje:String
)
/**
 * Creació de l'objecte Message.
 */
object Message {

    private val _llistaMensajes = MutableLiveData<ArrayList<Messages>>().apply {
        value = ArrayList<Messages>()
    }
    val llistaMensajes: MutableLiveData<ArrayList<Messages>> = _llistaMensajes


    fun getMessageAt(position: Int): Messages? {
        val messagesList = llistaMensajes.value
        return messagesList?.getOrNull(position)
    }

    fun size(): Int {
        return llistaMensajes.value?.size ?: 0
    }

    suspend fun sendMessage(nomUsuari: String, text: String) {
        GlobalScope.launch(Dispatchers.IO) {
            // Crear objeto JSON con el contenido del mensaje
            val jsonObject = JSONObject()
            jsonObject.put("nomUsuari", nomUsuari)
            jsonObject.put("mensaje", text)
            val jsonString = jsonObject.toString()

            // Llamar al método SendServer de la clase CommunicacionManager
            // (Asumiendo que tienes una clase CommunicacionManager con el método SendServer)
            CommunicationManager.SendServer(jsonString)


            /**
     * Funció que afegeix a la llista el mensaje.
     */
    fun add(nomUsuari: String, text: String) {
        val mensaje = Messages(nomUsuari, text)
        val messagesList = llistaMensajes.value ?: ArrayList()
        messagesList.add(mensaje)
        llistaMensajes.value = messagesList
    }
}
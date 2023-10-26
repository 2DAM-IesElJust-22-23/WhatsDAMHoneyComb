package com.ieseljust.pmdm.Model

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
object Message{

    var llistaMensajes=mutableListOf<Messages>()

    /**
     * Funció que afegeix a la llista el mensaje.
     */
    fun add (nomUsuari:String, text:String){

        var mensaje = Messages(nomUsuari,text)
        llistaMensajes.add(mensaje)
    }
}
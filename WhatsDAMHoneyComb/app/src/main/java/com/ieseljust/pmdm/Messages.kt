package com.ieseljust.pmdm

import java.util.ArrayList

class Messages(
    var nomUsuari:String,
    var mensaje:String
)

object Message{


    var listaMensajes=mutableListOf<Messages>()

    fun add (nomUsuari:String, text:String){

        var mensaje = Messages(nomUsuari,text)
        listaMensajes.add(mensaje)
    }
}
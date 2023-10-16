package com.ieseljust.pmdm

import java.util.ArrayList

class Messages(
    var nomUsuari:String,
    var mensaje:String
)

object Message{

    var llistaMensajes: ArrayList<Messages> = ArrayList<Messages>()

    fun add (nomUsuari:String, text:String){

        var mensaje = Messages(nomUsuari,text)
        llistaMensajes.add(mensaje)

    }
}
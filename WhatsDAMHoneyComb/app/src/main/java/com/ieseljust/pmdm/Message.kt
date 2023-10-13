package com.ieseljust.pmdm

import java.util.ArrayList

object Message{

    var llistaMensajes: ArrayList<Messages>

    init {
        llistaMensajes = ArrayList<Messages>()
    }

    fun add (nomUsuari:String, text:String){

        var mensaje = Messages(nomUsuari,text)
        llistaMensajes.add(mensaje)

    }



}

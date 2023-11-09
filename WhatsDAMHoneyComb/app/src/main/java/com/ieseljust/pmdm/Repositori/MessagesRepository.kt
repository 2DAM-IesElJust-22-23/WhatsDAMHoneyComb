package com.ieseljust.pmdm.Repositori


import com.ieseljust.pmdm.Model.Message
import com.ieseljust.pmdm.Model.Messages

class MessagesRepository private constructor() {

    companion object{
        private var INSTANCE : MessagesRepository? = null

        fun getInstance():MessagesRepository{

            if(INSTANCE == null){
                INSTANCE = MessagesRepository()
            }

            return INSTANCE !!
        }
    }

    /**
     * Obté la llistaMensajes.
     */
    fun getLlistaMensajes() = Message.llistaMensajes

    /**
     * Obté el tamany de la llistaMensajes.
     */
    fun getLlistaSize() = Message.llistaMensajes.size

    /**
     * Afig un mensaje a la llistaMensajes.
     */
    fun addMessage(nom:String, mText:String) {
        Message.add(nom, mText)
    }

    fun remove(m:Message) = Message.llistaMensajes.remove(m)
}
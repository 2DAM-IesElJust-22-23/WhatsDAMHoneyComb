package com.ieseljust.pmdm.Repositori

import com.ieseljust.pmdm.Model.Messages
import com.ieseljust.pmdm.Model.Message

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

    fun getLlistaMensajes() = Message.llistaMensajes
    fun getLlistaSize() = Message.llistaMensajes.size
    fun addMessage(nom:String, mText:String) {
        Message.add(nom, mText)
    }
}
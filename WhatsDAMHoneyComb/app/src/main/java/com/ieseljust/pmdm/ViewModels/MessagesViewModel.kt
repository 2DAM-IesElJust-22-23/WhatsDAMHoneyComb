package com.ieseljust.pmdm.ViewModels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ieseljust.pmdm.Model.Messages
import com.ieseljust.pmdm.Repositori.MessagesRepository

class MessagesViewModel(application: Application):AndroidViewModel(application){

    private val _adaptador = MutableLiveData<AdaptadorMessages>().apply ({
        value = AdaptadorMessages()
    })
    val adaptador:MutableLiveData<AdaptadorMessages> = _adaptador

    /**
     * Afig un mensaje fent crida la funció addMessages del Repositori i notifica al adaptador.
     */
    fun add(nom: String, mText: String){
        MessagesRepository.getInstance().addMessage(nom,mText)
        adaptador.value?.notifyItemInserted(MessagesRepository.getInstance().getLlistaSize() - 1)
    }

    private fun MessageLongClickedManager(messages: Messages, v: View):Boolean{
        val index = MessagesRepository.getInstance().remove(messages)
    }
}

package com.ieseljust.pmdm.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ieseljust.pmdm.Repositori.MessagesRepository


class MessagesViewModel(application: Application):AndroidViewModel(application){

    private val _adaptador = MutableLiveData<AdaptadorMessages>().apply ({
        value = AdaptadorMessages()
    })
    val adaptador:MutableLiveData<AdaptadorMessages> = _adaptador

    fun add(nom: String, mText: String){
        MessagesRepository.getInstance().addMessage(nom,mText)
        adaptador.value?.notifyItemInserted(MessagesRepository.getInstance().getLlistaSize() - 1)
    }
}

package com.ieseljust.pmdm.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ieseljust.pmdm.Repositori.MessagesRepository


class MessagesViewModel(application: Application):AndroidViewModel(application){

    private val _adaptador = MutableLiveData<AdaptadorMessages>().apply ({
        value = AdaptadorMessages(getApplication<Application>().applicationContext)
    })
    val adaptador:MutableLiveData<AdaptadorMessages> = _adaptador

    public fun add(){
        if(MessagesRepository.getInstance().addMessage()){
            adaptador.value?.notifyItemInserted(MessagesRepository.getInstance().getLlistaSize() - 1)
        }
    }


}

package com.ieseljust.pmdm.ViewModels

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ieseljust.pmdm.Model.Messages
import com.ieseljust.pmdm.R
import com.ieseljust.pmdm.Repositori.MessagesRepository

class AdaptadorMessages(val context:Context) :RecyclerView.Adapter<MessageViewHolder>(){

    /*
    // Funció que crea una nova vista d'element en la llista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater=LayoutInflater.from((parent.context))
        val vista= inflater.inflate(
            R.layout.my_msg_viewholder,
            parent, false)
        return MessageViewHolder(vista)
    }

    /*
    // Funció que actualitza una vista d'element amb dades específiques.
    */
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(MessagesRepository.getInstance().getLlistaMensajes()[position])
    }

    /*
    // Funció per a obtindre la quantitar d'elements en la llista.
     */
    override fun getItemCount(): Int {
        return MessagesRepository.getInstance().getLlistaSize()
    }




}
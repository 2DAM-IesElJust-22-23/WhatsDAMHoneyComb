package com.ieseljust.pmdm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class AdaptadorMessages(var listaMensajes: List<Messages>) :RecyclerView.Adapter<MessageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater=LayoutInflater.from((parent.context))
        val vista= inflater.inflate(
            R.layout.my_msg_viewholder,
            parent, false)
        return MessageViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(listaMensajes[position])
    }

    override fun getItemCount(): Int {
        return Message.listaMensajes.size
    }




}
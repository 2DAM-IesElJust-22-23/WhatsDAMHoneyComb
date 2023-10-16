package com.ieseljust.pmdm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.EventListener

class AdaptadorMessages (
    val context: Context,
    val eventListener: (Message,View) -> Boolean)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater=LayoutInflater.from((parent.context))
        val vista= inflater.inflate(
            R.layout.my_msg_viewholder,
            parent, false)
        return MessageViewHolder(vista, context)
    }

    override fun getItemCount(): Int {
        return Message.llistaMensajes.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int) {
        (holder as MessageViewHolder).bind(
            Message.llistaMensajes[position],
            eventListener)
    }


}
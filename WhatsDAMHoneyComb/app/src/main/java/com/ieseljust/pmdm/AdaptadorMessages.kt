package com.ieseljust.pmdm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class AdaptadorMessages (val context: Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater=LayoutInflater.from((parent.context))
        val vista= inflater.inflate(
            R.layout.my_msg_viewholder,
            parent, false)
        return MessageViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return Message.llistaMensajes.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int) {
        (holder as MessageViewHolder).bind(
            Message.llistaMensajes[position])
    }


}
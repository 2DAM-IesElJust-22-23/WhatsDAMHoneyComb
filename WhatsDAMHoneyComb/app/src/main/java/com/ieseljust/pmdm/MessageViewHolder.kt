package com.ieseljust.pmdm

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class MessageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    private val mText = itemView.findViewById(R.id.msg_text) as TextView
    private val hora = itemView.findViewById(R.id.msg_me_timestamp) as TextView

    fun bind(message: Messages){

        mText.text = message.mensaje

        val dataFormat = SimpleDateFormat("HH:MM")
        val horaAct = Date()
        val horaF = dataFormat.format(horaAct)

        hora.text = horaF.toString()

    }
}
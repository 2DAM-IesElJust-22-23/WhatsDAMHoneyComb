package com.ieseljust.pmdm.ViewModels

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ieseljust.pmdm.Model.Messages
import com.ieseljust.pmdm.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.EventListener

class MessageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    private val mText = itemView.findViewById(R.id.msg_text) as TextView
    private val hora = itemView.findViewById(R.id.msg_me_timestamp) as TextView

    /**
     * Funció que s'utiliza per a vincular les dades del mensaje al diseny.
     */
    fun bind(message: Messages, eventListener: (Messages,View) -> Boolean){

        mText.text = message.mensaje

        /**
         * Variables per a configurar l'hora actual.
         */
        val dataFormat = SimpleDateFormat("HH:MM")
        val horaAct = Date()
        val horaF = dataFormat.format(horaAct)

        hora.text = horaF.toString()

        itemView.setOnLongClickListener(
            eventListener(message, itemView)
        )

    }
}
package com.ieseljust.pmdm



import android.view.View
import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageViewHolder(itemView:View, val context: Context) : View.OnClickListener,RecyclerView.ViewHolder(itemView) {


    private val mText = itemView.findViewById(R.id.msg_text) as TextView
    private val nom = itemView.findViewById(R.id.msg_me_timestamp) as TextView


    init{
        itemView.setOnClickListener(this)
    }
    fun bind(
        Messages: Messages,
        eventListener: (Message, View) -> Boolean) {
        nom.text = Messages.nomUsuari
        mText.text = Messages.mensaje
    }
    override fun onClick(p0: View?) {
    }
}
package com.ieseljust.pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieseljust.pmdm.Message.listaMensajes
import com.ieseljust.pmdm.databinding.ActivityMessagesWindowBinding



class MessagesWindow : AppCompatActivity() {
    lateinit var binding: ActivityMessagesWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nick = intent.getStringExtra("nickname")
        val ipVal = intent.getStringExtra("ipOk")

        binding.connectionInfoTextView.text = "Connectat a " + ipVal + " com a " + nick


        val messageText=binding.MessageText
        val sendMessage =binding.sendMessage


        val recyclerView=binding.MessagesRecyclerView


        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter = AdaptadorMessages(listaMensajes)
        recyclerView.adapter =adapter



        sendMessage.setOnClickListener {
            listaMensajes.add(Messages(nick.toString(),messageText.text.toString()))
            binding.MessagesRecyclerView.adapter?.notifyItemInserted(listaMensajes.size-1)
            recyclerView.scrollToPosition(listaMensajes.size-1)
            messageText.text.clear()
        }
    }
}
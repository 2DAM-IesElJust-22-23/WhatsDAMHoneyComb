package com.ieseljust.pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ieseljust.pmdm.databinding.ActivityMessagesWindowBinding


class MessagesWindow : AppCompatActivity() {
    lateinit var binding: ActivityMessagesWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message= intent.getStringExtra("nickname")
        val ipVal= intent.getStringExtra("ipOk")

        binding.connectionInfoTextView.text =  "Connectat a "+ipVal+" com a "+ message

        binding.sendMessage.setOnClickListener{
            binding.MessageText.text.clear()
        }
    }
}
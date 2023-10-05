package com.example.whatsdamhoneycomb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsdamhoneycomb.databinding.ActivityMessagesWindowBinding


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
            binding.messageText.text.clear()
        }
    }
}


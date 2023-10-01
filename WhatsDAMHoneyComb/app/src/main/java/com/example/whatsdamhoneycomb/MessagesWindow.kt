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

        binding.sendMessage.setOnClickListener{
            binding.messageText.text.clear()
        }
    }
}


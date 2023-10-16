package com.ieseljust.pmdm

import android.content.Intent
import android.net.InetAddresses.isNumericAddress
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieseljust.pmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConnect.setOnClickListener() {

            /*
             * Crear variables nombre e ip para luego enviarlo con un intent
             */
            
            val intent = Intent(baseContext, MessagesWindow::class.java)
            var nickname = binding.nickNameText.text.toString()
            var ipOk = binding.serverAddressText.text.toString()

            if (nickname != "") {
                intent.putExtra("nickname", nickname)
                if (isNumericAddress(ipOk))
                    intent.putExtra("ipOk", ipOk)
                startActivity(intent)
            }

        }

    }
    
}
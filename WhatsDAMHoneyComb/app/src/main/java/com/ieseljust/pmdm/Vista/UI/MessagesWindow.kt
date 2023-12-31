package com.ieseljust.pmdm.Vista.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieseljust.pmdm.Model.Message.llistaMensajes
import com.ieseljust.pmdm.ViewModels.MessagesViewModel
import com.ieseljust.pmdm.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {
    lateinit var binding: ActivityMessagesWindowBinding
    lateinit var viewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this)[MessagesViewModel::class.java]


        val nick = intent.getStringExtra("nickname")
        val ipVal = intent.getStringExtra("ipOk")

        binding.connectionInfoTextView.text = "Connectat a " + ipVal + " com a " + nick


        val messageText=binding.MessageText
        val sendMessage =binding.sendMessage


        val recyclerView=binding.MessagesRecyclerView


        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        /**
         * Aquesta variable conte l'adaptador que s'utilitza per a proporcionar les dades i
         * gestionar la visualització dels missatges en la llista.
         */
        viewModel.adaptador.observe(this){
            recyclerView.adapter =it
        }
        /**
         * S'estableix un OnClickListener en el botó sendMessage. Quant es fa clic en el botó,
         * s'agrega un nou missatge a la llista, es notifica a l'adaptador perquè
         * actualitze la vista, es desplaça la vista del *RecyclerView al nou missatge i es neteja el camp d'entrada de text.
         */
        sendMessage.setOnClickListener {
            viewModel.add(nick.toString(),messageText.text.toString())
            binding.MessagesRecyclerView.adapter?.notifyItemInserted(llistaMensajes.size-1)
            recyclerView.scrollToPosition(llistaMensajes.size-1)
            messageText.text.clear()
        }
    }
}
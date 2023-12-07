package com.tutorials.ktorclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tutorials.ktorclient.data.remote.websocket.SocketServiceImpl
import com.tutorials.ktorclient.databinding.ActivitySocketBinding
import kotlinx.coroutines.launch

class SocketActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocketBinding
//    private val socketService = CoinbaseServiceImpl()
    private val socketService = SocketServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)



/* KTOR COINBASE

      binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.connectToCoinbase {
                    socketService.subscribeToCoinbase()
                    */
/*Works
                    socketService.receiveIncomingData(it).collect{value->
                        Log.d("JOEKTORCLIENT", "..observing->$value")
                    }*//*

                    */
/*Works
                    socketService.receiveIncomingData().collect {
                        binding.resultText.text = it
                    }*//*



                }
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.closeSocket()
            }
        }
*/




        binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.connectToSocket {
//                        socketService.receiveIncomingData2()
                    socketService.receiveIncomingData().collect {
                        binding.resultText.text = it
                    }


                }
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.closeSocket()
            }
        }

        binding.sendPostBtn.setOnClickListener {
            val text = binding.bodyEdt.text.toString().trim()
            if (text.isEmpty()){
                return@setOnClickListener
            }

            lifecycleScope.launch {
                socketService.sendToSocket(text)
            }
        }

    }
}
package com.tutorials.ktorclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.tutorials.ktorclient.data.remote.jagha.constants.Constant
import com.tutorials.ktorclient.data.remote.jagha.constants.Constant.BASE_WEBSOCKET_URL
import com.tutorials.ktorclient.data.remote.jagha.model.request.ConnectPlaceChatRequest
import com.tutorials.ktorclient.data.remote.websocket.OkhttpWebSocket
import com.tutorials.ktorclient.data.remote.websocket.SocketServiceImpl
import com.tutorials.ktorclient.databinding.ActivitySocketBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class SocketActivity : AppCompatActivity() {
    private val SOCKETBAY_URL = "wss://socketsbay.com/wss/v2/1/demo/"
    private val COINBASE_URL = "wss://ws-feed.pro.coinbase.com"
    private lateinit var binding: ActivitySocketBinding
//    private val socketService = CoinbaseServiceImpl()
    private val socketService = SocketServiceImpl()
    private lateinit var webSocket:WebSocket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_WEBSOCKET_URL)
            .build()
        val listener = OkhttpWebSocket()
        binding.connectBtn.setOnClickListener {
            webSocket = client.newWebSocket(request,listener)
        }

        binding.sendPostBtn.setOnClickListener {
           val data = ConnectPlaceChatRequest(
               message_type = Constant.MessageType.connectPlaceChat,
               place_id = "ChIJ1cVKtYLQxTsR0BA3yOadcTw"
           )

            val json = Gson().toJson(data)

            lifecycleScope.launch {
               webSocket.send(json)
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                webSocket.close(1000,"Close by user")
            }
        }


       /* OKHTTP SOCKET BAY
        binding.sendPostBtn.setOnClickListener {
            val text = binding.bodyEdt.text.toString().trim()
            if (text.isEmpty()){
                return@setOnClickListener
            }

            lifecycleScope.launch {
               webSocket.send(text)
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                webSocket.close(1000,"Close by user")
            }
        }*/

        observeResultsFlow(listener)



      /* KTOR  COINBASE
      binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.connectToCoinbase {
                    socketService.subscribeToCoinbase()
                    observeResults()
                }
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.closeSocket()
            }
        }*/

       /* KTOR SOCKETBAY
       binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.connectToSocket {
                    observeResults()
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
        }*/
    }

    private fun observeResults() {
        lifecycleScope.launch {
            socketService.receiveIncomingData().collect {
                binding.resultText.text = it
            }
        }

    }
     private fun observeResultsFlow(listener: OkhttpWebSocket) {
        lifecycleScope.launch {
            listener.resultFlow.collect {
                binding.resultText.text = it
            }
        }

    }

    //refactored format ...
    private fun okhttpJagha(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_WEBSOCKET_URL)
            .build()
        val listener = OkhttpWebSocket()
        binding.connectBtn.setOnClickListener {
            webSocket = client.newWebSocket(request,listener)
        }

        binding.sendPostBtn.setOnClickListener {
            val data = ConnectPlaceChatRequest(
                message_type = Constant.MessageType.connectPlaceChat,
                place_id = "ChIJ1cVKtYLQxTsR0BA3yOadcTw"
            )

            val json = Gson().toJson(data)

            lifecycleScope.launch {
                webSocket.send(json)
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                webSocket.close(1000,"Close by user")
            }
        }
        observeResultsFlow(listener)
    }

}
package com.tutorials.ktorclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant
import com.tutorials.ktorclient.data.remote.websocket.jagha.ConnectionState
import com.tutorials.ktorclient.data.remote.websocket.jagha.JaghaServiceImpl
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.ConnectPlaceChatRequest
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.DisconnectPlaceChatRequest
import com.tutorials.ktorclient.databinding.ActivitySocketBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SocketActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocketBinding
    private val socketService = JaghaServiceImpl()
//    private val newScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            socketService.connectionState.collect{state->
                when(state){
                    ConnectionState.IDLE->{
                        binding.errorText.text = "Idle"
                    }
                    ConnectionState.CONNECTING->{
                        binding.errorText.text = "Connecting"
                    }
                    ConnectionState.NOT_CONNECTED->{
                        binding.errorText.text = "Not Connected"
                    }
                    ConnectionState.CONNECTED->{
                        binding.errorText.text = "Connected"
                    }
                    ConnectionState.DISCONNECTING->{
                        binding.errorText.text = "Disconnecting"
                    }
                    ConnectionState.DISCONNECTED->{
                        binding.errorText.text = "Disconnected"
                    }
                    ConnectionState.NOT_DISCONNECTED->{
                        binding.errorText.text = "Disconnected"
                    }
                    ConnectionState.SENDING->{
                        binding.errorText.text = "Sending"
                    }
                    ConnectionState.SENT->{
                        binding.errorText.text = "Sent"
                    }
                    ConnectionState.NOT_SENT->{
                        binding.errorText.text = "Not sent"
                    }

                }
            }
        }

        binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.initConnection {
                    socketService.receiveIncomingData().collect {
                        binding.resultText.text = it
                    }

                }
            }
        }

        binding.disconnectBtn.setOnClickListener {
            lifecycleScope.launch {
                val request = DisconnectPlaceChatRequest(
                    messageType = Constant.MessageType.disconnectPlaceChat,
                    placeId = "ChIJ1cVKtYLQxTsR0BA3yOadcTw"
                )
                socketService.disconnectPlaceChat(request)
                delay(3000L)
                socketService.closeSocket()
            }
        }

        binding.sendPostBtn.setOnClickListener {
            lifecycleScope.launch {

                val request = ConnectPlaceChatRequest(
                    messageType = Constant.MessageType.connectPlaceChat,
                    placeId = "ChIJ1cVKtYLQxTsR0BA3yOadcTw"
                )
                socketService.connectPlaceChat(request)
            }
        }
    }
}

/* KTOR COINBASE

        binding.connectBtn.setOnClickListener {
            lifecycleScope.launch {
                socketService.connectToCoinbase {
                    socketService.subscribeToCoinbase()

                    socketService.receiveIncomingData(it).collect { value ->
                        Log.d("JOEKTORCLIENT", "..observing->$value")
                    }


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
*/

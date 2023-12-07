package com.tutorials.ktorclient.data.remote.websocket

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.websocket.Frame
import io.ktor.websocket.closeExceptionally
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json

class SocketServiceImpl {
    private val SOCKETBAY_URL = "wss://socketsbay.com/wss/v2/1/demo/"
    private var coinSocket: DefaultClientWebSocketSession? = null
    private val api: HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }

    }


    suspend fun connectToSocket(onConnect:suspend (DefaultClientWebSocketSession) -> Unit) {
        try {
            api.webSocket(SOCKETBAY_URL) {
                if (!isActive) {
                    Log.d("JOEKTORCLIENT", "Error 1 to Connecting Socket")
                    return@webSocket
                }
                coinSocket = this
                onConnect(this)
                Log.d("JOEKTORCLIENT", "Success Connecting Socket")
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Connecting Socket: $e")
        }
    }


    suspend fun sendToSocket(text:String) {
        try {
            coinSocket?.let { cs ->
                cs.send(Frame.Text(text))
                Log.d("JOEKTORCLIENT", "Success Sending Socket")
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Subscribing Socket: $e")
        }
    }

     fun receiveIncomingData(): Flow<String> =
        try {
            coinSocket?.let { cs ->
                Log.d("JOEKTORCLIENT", "Success Subscribing to receive Socket")
                cs.incoming.receiveAsFlow().filter { it is Frame.Text }
                    .map {
                     (it as Frame.Text).readText()//format the data to fit your usecase
                    }
            } ?: flow { "Nullable error" }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error receiving message: $e")
            flow { "BIG ERROR: Some error: ${e.message}" }
        }
    suspend fun receiveIncomingData2() {
        try {
            coinSocket?.let {cs->
                Log.d("JOEKTORCLIENT", "Success Subscribing to receive Socket")
                cs.incoming.receiveAsFlow().collect{
                    Log.d("JOEKTORCLIENT", "INCOMING: $it")
                }
            }

        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error receiving message: $e")

        }
    }
    suspend fun closeSocket() {
        try {
            coinSocket?.closeExceptionally(Exception("Done with usage or something"))
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Closing Socket: $e")
        }
    }
}


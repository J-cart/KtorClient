package com.tutorials.ktorclient.data.remote.websocket

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
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

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

    }


    suspend fun connectToSocket(onConnect:suspend () -> Unit) {
        try {
            api.webSocket(SOCKETBAY_URL) {
                if (isActive) {
                    coinSocket = this
                    onConnect()
                    Log.d("JOEKTORCLIENT", "Success Connecting Socket")
                }
                Log.d("JOEKTORCLIENT", "Error 1 to Connecting Socket")
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Connecting Socket: $e")
        }
    }


    suspend fun sendToSocket(text:String) {
        try {
            coinSocket?.let { cs ->
                if (cs.isActive) {
                    cs.send(Frame.Text(text))
                    Log.d("JOEKTORCLIENT", "Success Sending Socket")
                }
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Subscribing Socket: $e")
        }
    }

     fun receiveIncomingData(): Flow<String> =
        try {
            coinSocket?.let { cs ->
                if (!cs.isActive){
                    return@let flow { "NOT ACTIVE" }
                }
                Log.d("JOEKTORCLIENT", "Success Subscribing Socket")
                cs.incoming.receiveAsFlow().filter { it is Frame.Text }
                    .map {
                       val text = (it as Frame.Text).readText()
                        Json.decodeFromString(text)
                    }
            } ?: flow { "Nullable error" }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error receiving message: $e")
            flow { "BIG ERROR: Some error: ${e.message}" }
        }

    suspend fun closeSocket() {
        try {
            coinSocket?.closeExceptionally(Exception("Done with usage or something"))
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Closing Socket: $e")
        }
    }
}


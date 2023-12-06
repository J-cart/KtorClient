package com.tutorials.ktorclient.data.remote.websocket

import android.util.Log
import com.tutorials.ktorclient.data.remote.websocket.helper.Crypto
import com.tutorials.ktorclient.data.remote.websocket.model.Subscribe
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.sendSerialized
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

class CoinbaseServiceImpl {
    private val COINBASE_URL = "wss://ws-feed.pro.coinbase.com"
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


    suspend fun connectToCoinbase(onConnect:suspend () -> Unit) {
        try {
            api.webSocket(COINBASE_URL) {
                if (isActive) {
                    coinSocket = this
                    onConnect()
                    Log.d("JOEKTORCLIENT", "Success Connecting Socket")
                    return@webSocket
                }
                Log.d("JOEKTORCLIENT", "Error 1 to Connecting Socket")
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Connecting Socket: $e")
        }
    }


    suspend fun subscribeToCoinbase() {
        try {
            coinSocket?.let { cs ->
                cs.sendSerialized(
                    Subscribe(
                        "subscribe",
                        Crypto.values().map { it.id },
                        listOf("ticker")
                    )
                )
                Log.d("JOEKTORCLIENT", "Success Subscribing Socket")
            }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error Subscribing Socket: $e")
        }
    }

     fun receiveIncomingData(): Flow<String> =
        try {
            coinSocket?.let { cs ->
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


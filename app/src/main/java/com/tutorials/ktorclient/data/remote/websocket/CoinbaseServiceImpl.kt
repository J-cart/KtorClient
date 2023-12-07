package com.tutorials.ktorclient.data.remote.websocket

import android.util.Log
import com.tutorials.ktorclient.data.remote.websocket.helper.Crypto
import com.tutorials.ktorclient.data.remote.websocket.model.Subscribe
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.sendSerialized
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

       /* defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }*/

    }


    suspend fun connectToCoinbase(onConnect:suspend (DefaultClientWebSocketSession) -> Unit) {
        try {
            api.webSocket(COINBASE_URL) {
                if (isActive) {
                    coinSocket = this
                    onConnect(this)
                    /*
                    seems observing directly in the scope of this init block works, check the usage in SocketActivity.kt
                    */
                    /*WORKS
                    incoming.receiveAsFlow().collect{
                        Log.d("JOEKTORCLIENT", "TOP INCOMING: $it")
                    }*/

                    /*WORKS
                    receiveIncomingData(this).collect{
                        Log.d("JOEKTORCLIENT", "TOP INCOMING: $it")
                    }*/
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

    //This works if placed in the websocket init block also check its usage in SocketActivity.kt
    fun receiveIncomingData(): Flow<String> =
        try {
            coinSocket?.let { cs ->
                Log.d("JOEKTORCLIENT", "Success Subscribing Socket")
                cs.incoming.receiveAsFlow().filter { it is Frame.Text }
                    .map {
                        (it as Frame.Text).readText()//format the data to fit your usecase
                    }
            } ?: flow { "Nullable error" }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error receiving message: $e")
            flow { "BIG ERROR: Some error: ${e.message}" }
        }

    //This works if placed in the websocket init block also check its usage in SocketActivity.kt
     fun receiveIncomingData(socket: DefaultClientWebSocketSession): Flow<String> =
        try {
            Log.d("JOEKTORCLIENT", "Success Subscribing Socket")
            socket.incoming.receiveAsFlow().filter { it is Frame.Text }
                .map {
                    (it as Frame.Text).readText()//format the data to fit your usecase
                }
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


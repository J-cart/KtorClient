package com.tutorials.ktorclient.data.remote.websocket.jagha

import android.util.Log
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant.BASE_WEBSOCKET_URL
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.ConnectPlaceChatRequest
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.DisconnectPlaceChatRequest
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.SetMessageRequest
import com.tutorials.ktorclient.data.remote.websocket.jagha.request.UserProfileRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.websocket.Frame
import io.ktor.websocket.closeExceptionally
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


class JaghaServiceImpl {
    var connectionState = MutableStateFlow<ConnectionState>(ConnectionState.IDLE)
        private set
    private var jaghaSocket: DefaultClientWebSocketSession? = null
    @OptIn(ExperimentalSerializationApi::class)
    private val api: HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }

       /* NOT TESTED YET, so i'm not sure of its effect
       install(ContentNegotiation) {
            register(
                ContentType.Text.Any, KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            )
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })
        }*/

        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
            header(Constant.APP_VERSION, "1")
            header(Constant.TOKEN, Constant.USER_TOKEN)
        }

    }


    suspend fun initConnection(onConnect:suspend (DefaultClientWebSocketSession) -> Unit) {
        connectionState.value = ConnectionState.CONNECTING
        try {
            api.webSocket(BASE_WEBSOCKET_URL) {
                if (isActive) {
                    jaghaSocket = this
                    connectionState.value = ConnectionState.CONNECTED
                /*  WORKS
                  val json = JSONObject().put("message_type","user_get")
                   send(json.toString())*/

//                    sendSerialized(UserProfileRequest(Constant.MessageType.getUser))
                    onConnect(this)
                    /*getUser(UserProfileRequest(Constant.MessageType.getUser))*/
                    /*receiveIncomingData().collect {
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



    //This works if placed in the websocket init block also check its usage in SocketActivity.kt
    fun receiveIncomingData(): Flow<String> =
        try {
            jaghaSocket?.let { cs ->
                Log.d("JOEKTORCLIENT", "Success Subscribing Socket")
                cs.incoming.receiveAsFlow().filter { it is Frame.Text }
                    .map {
                        val text =  (it as Frame.Text).readText()//format the data to fit your usecase
                        Log.d("JOEKTORCLIENT", "receiving here: $text")
                        text

                    }
            } ?: flow { "Nullable error" }
        } catch (e: Exception) {
            Log.d("JOEKTORCLIENT", "Error receiving message: $e")
            flow { "BIG ERROR: Some error: ${e.message}" }
        }

     suspend fun getUser(request: UserProfileRequest) {
         connectionState.value = ConnectionState.SENDING
        try {
            jaghaSocket?.let {
//                val json = JSONObject().put("message_type","user_get")
//                it.send(json.toString())
                it.sendSerialized(request)
                connectionState.value = ConnectionState.SENT
                Log.d("JOEKTORCLIENT", "Success sending message to Socket")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            connectionState.value = ConnectionState.NOT_SENT
            Log.d("JOEKTORCLIENT", "Error sending message: $e")
        }
    }

    suspend fun sendMessage(request: SetMessageRequest) {
        try {
            jaghaSocket?.let {
                it.sendSerialized(request)
                Log.d("JOEKTORCLIENT", "Success sending message to Socket")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("JOEKTORCLIENT", "Error sending message: $e")
        }
    }

     suspend fun connectPlaceChat(request: ConnectPlaceChatRequest) {
        try {
            jaghaSocket?.let {
                it.sendSerialized(request)
                Log.d("JOEKTORCLIENT", "Success sending connect place to Socket")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("JOEKTORCLIENT", "Error sending connect to place message: $e")
        }
    }

     suspend fun disconnectPlaceChat(request: DisconnectPlaceChatRequest) {
        try {
            jaghaSocket?.let {
                it.sendSerialized(request)
                Log.d("JOEKTORCLIENT", "Success sending disconnect place to Socket")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("JOEKTORCLIENT", "Error sending disconnect place message: $e")
        }
    }


    suspend fun closeSocket() {
        connectionState.value = ConnectionState.DISCONNECTING
        try {
            jaghaSocket?.closeExceptionally(Exception("Done with usage or something"))
            connectionState.value = ConnectionState.DISCONNECTED
        } catch (e: Exception) {
            connectionState.value = ConnectionState.NOT_DISCONNECTED
            Log.d("JOEKTORCLIENT", "Error Closing Socket: $e")
        }
    }
}

enum class ConnectionState{
    CONNECTING,
    CONNECTED,
    NOT_CONNECTED,
    DISCONNECTING,
    DISCONNECTED,
    NOT_DISCONNECTED,
    SENDING,
    SENT,
    NOT_SENT,
    IDLE
}


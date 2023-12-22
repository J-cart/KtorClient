package com.tutorials.ktorclient.data.remote.websocket

import android.util.Log
import com.google.gson.Gson
import com.tutorials.ktorclient.data.remote.jagha.model.request.ConnectPlaceChatRequest
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class OkhttpWebSocket: WebSocketListener() {
    var resultFlow = MutableStateFlow("Awaiting ...")
        private set

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d("JOEKTORCLIENT", "closed Socket: reason -> $reason")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.d("JOEKTORCLIENT", "on closing Socket: reason -> $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d("JOEKTORCLIENT", "on failure Socket: reason -> $t")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.d("JOEKTORCLIENT", "MESSAGE RECEIVED(text): $text")
        resultFlow.value = text
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.d("JOEKTORCLIENT", "MESSAGE RECEIVED(byte): $bytes")
        resultFlow.value = bytes.toString()
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d("JOEKTORCLIENT", "on open Socket: response -> $response")
        val data = ConnectPlaceChatRequest(
            message_type = "connect_place_chat",
            place_id = "ChIJ1cVKtYLQxTsR0BA3yOadcTw"
        )

        val json = Gson().toJson(data)
        Log.d("JOEKTORCLIENT", "json-> $json")
        webSocket.send(json)
    }
}
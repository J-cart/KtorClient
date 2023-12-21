package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnectPlaceChatRequest(
    @SerialName("place_id")
    val placeId: String,

    @SerialName("message_type")
    val messageType: String ,
)
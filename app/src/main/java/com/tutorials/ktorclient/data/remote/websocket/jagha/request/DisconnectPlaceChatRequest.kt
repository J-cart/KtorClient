package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.tutorials.ktorclient.data.remote.websocket.helper.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisconnectPlaceChatRequest(
    @SerialName("place_id")
    val placeId: String,

    @SerialName("message_type")
    val messageType: String = Constant.MessageType.disconnectPlaceChat,
)

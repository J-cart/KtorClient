package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.tutorials.ktorclient.data.remote.websocket.helper.Constant
import kotlinx.serialization.Serializable

@Serializable
data class SetMessageRequest(
//    @SerialName("place_id")
    val place_id: String,
//    @SerialName("message")
    val message: String,

//    @SerialName("message_type")
    val message_type: String = Constant.MessageType.setMessage,
)
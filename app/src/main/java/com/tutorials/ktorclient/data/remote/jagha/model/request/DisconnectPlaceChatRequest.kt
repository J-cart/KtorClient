package com.tutorials.ktorclient.data.remote.jagha.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisconnectPlaceChatRequest(
    @SerialName("place_id")
    val placeId: String,

    @SerialName("message_type")
    val messageType: String
)

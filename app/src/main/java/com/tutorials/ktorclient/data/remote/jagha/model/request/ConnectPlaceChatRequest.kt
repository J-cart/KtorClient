package com.tutorials.ktorclient.data.remote.jagha.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnectPlaceChatRequest(
    @SerialName("place_id")
    val place_id: String,

    @SerialName("message_type")
    val message_type: String
)
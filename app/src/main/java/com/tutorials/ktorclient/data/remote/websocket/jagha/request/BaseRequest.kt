package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseRequest(
    @SerialName("message_type")
    val messageType: String,

    @SerialName("last_id")
    val lastId: String = "",
    @SerialName("rows")
    val rows: Int = 100000,
)
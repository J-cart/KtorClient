package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName

data class IdRequest(
    @SerializedName("id")
    val id: String,

    @SerializedName("message_type")
    val messageType: String,
)
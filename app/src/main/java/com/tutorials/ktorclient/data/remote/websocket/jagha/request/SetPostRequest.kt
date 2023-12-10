package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class SetPostRequest(
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("message")
    val message: String,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.setPost,
)
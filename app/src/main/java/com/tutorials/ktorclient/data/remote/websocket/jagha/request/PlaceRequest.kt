package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class PlaceRequest(
    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.getPlace,
    @SerializedName("place_id")
    val place_id: String,
)

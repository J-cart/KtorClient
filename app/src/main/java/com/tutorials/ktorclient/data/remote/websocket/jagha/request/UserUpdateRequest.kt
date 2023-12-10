package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class UserUpdateRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.userUpdate,
)
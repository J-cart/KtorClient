package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName

data class PostIdRequest(
    @SerializedName("post_id")
    val postId: String,

    @SerializedName("message_type")
    val messageType: String,
)
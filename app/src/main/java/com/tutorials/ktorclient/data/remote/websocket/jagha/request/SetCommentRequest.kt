package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class SetCommentRequest(
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("message")
    val message: String,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.setComment,
)
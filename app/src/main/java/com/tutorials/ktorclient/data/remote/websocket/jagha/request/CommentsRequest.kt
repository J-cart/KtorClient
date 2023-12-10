package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class CommentsRequest(
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("last_id")
    val post_id: String = "", // Last post id for pagination
    @SerializedName("rows")
    val rows: Int = 100,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.getPostComments,
)
package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class PostsForPlaceRequest(
    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.getPostsForPlace,
    @SerializedName("place_id")
    val place_id: String,
    @SerializedName("rows")
    val rows: Int = 100,
    @SerializedName("last_id")
    val post_id: String = "", // Last post id for pagination
)
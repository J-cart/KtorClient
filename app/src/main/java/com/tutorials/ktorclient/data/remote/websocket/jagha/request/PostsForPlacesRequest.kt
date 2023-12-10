package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class PostsForPlacesRequest(
    @SerializedName("ids")
    val placeIds: List<String>,
    @SerializedName("rows")
    val rows: Int = 100000,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.latestPostsFromPlaces,
)
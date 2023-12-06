package com.tutorials.ktorclient.data.remote.websocket.model

import kotlinx.serialization.Serializable

//@JsonClass(generateAdapter = true)
@Serializable
data class Subscribe(
//    @SerialName("type")
    val type: String,
//    @SerialName("product_ids")
    val product_ids: List<String>,
//    @SerialName("channels")
    val channels: List<String>,
)

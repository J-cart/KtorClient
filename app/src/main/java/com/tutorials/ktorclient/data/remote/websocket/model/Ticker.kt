package com.tutorials.ktorclient.data.remote.websocket.model

import kotlinx.serialization.Serializable

@Serializable
data class Ticker(
//    @SerialName("product_id")
    val product_ids: String = "",
    val price: String = "",
)

package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import com.google.gson.annotations.SerializedName
import com.tutorials.ktorclient.data.remote.websocket.helper.Constant

data class SetReportRequest(
    @SerializedName("type_id")
    val id: String,
    @SerializedName("type")
    val type: Int,

    @SerializedName("message_type")
    val messageType: String = Constant.MessageType.setReport,
)
package com.tutorials.ktorclient.data.remote.websocket.jagha.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileRequest(
    @SerialName("message_type")
    val message_type:String //= "user_get"//Constant.MessageType.getUser,
//    val token:String for some reason using this causes cancellation exception, is it supposed to not be added at runtime
    //    but at initialization of the websocket as a header parameter?
)

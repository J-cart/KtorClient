package com.tutorials.ktorclient.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestModel(
    val userId:Int,
    val title:String,
    val body:String
)
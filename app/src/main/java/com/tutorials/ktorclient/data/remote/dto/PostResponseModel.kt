package com.tutorials.ktorclient.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseModel(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
)

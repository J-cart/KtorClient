package com.tutorials.ktorclient.data.remote

import com.tutorials.ktorclient.data.remote.dto.PostRequestModel
import com.tutorials.ktorclient.data.remote.dto.PostResponseModel

interface JsonPlaceHolderService {

    suspend fun getPosts():List<PostResponseModel>
    suspend fun getPost(postId:Int):PostResponseModel?
    suspend fun createPost(postRequestModel: PostRequestModel):PostRequestModel?
}
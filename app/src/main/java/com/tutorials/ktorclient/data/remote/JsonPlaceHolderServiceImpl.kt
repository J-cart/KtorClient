package com.tutorials.ktorclient.data.remote

import android.util.Log
import com.tutorials.ktorclient.data.remote.dto.PostRequestModel
import com.tutorials.ktorclient.data.remote.dto.PostResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class JsonPlaceHolderServiceImpl():JsonPlaceHolderService {
    private val api: HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation){
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

    }



    override suspend fun getPosts(): List<PostResponseModel> {
        return try {
            api.get {
                url(JsonPlaceHolderRoutes.POST)
            }.body<List<PostResponseModel>>().apply {
                Log.d("JOEKTORTAG","SUCCESS:($size)")
            }
        }catch (ex: Exception) {
            Log.d("JOEKTORTAG","Error: $ex")
            emptyList()
        }
    }

    override suspend fun getPost(postId:Int):PostResponseModel? {
        return try {
            api.get {
                url(JsonPlaceHolderRoutes.POST+"/$postId")
            }.body<PostResponseModel>().apply {
                Log.d("JOEKTORTAG","SUCCESS:($this)")
            }
        }catch (ex: Exception) {
            Log.d("JOEKTORTAG","Error: $ex")
            null
        }
    }

    override suspend fun createPost(postRequestModel: PostRequestModel): PostRequestModel? {
        return try {
            api.post {
                url(JsonPlaceHolderRoutes.POST)
                contentType(ContentType.Application.Json)
                setBody(postRequestModel)
            }.body()
        }catch (ex: Exception) {
            Log.d("JOEKTORTAG","Error: $ex")
            null
        }
    }
}
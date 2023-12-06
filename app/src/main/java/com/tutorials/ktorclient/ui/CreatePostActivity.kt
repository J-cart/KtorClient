package com.tutorials.ktorclient.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tutorials.ktorclient.data.remote.JsonPlaceHolderServiceImpl
import com.tutorials.ktorclient.data.remote.dto.PostRequestModel
import com.tutorials.ktorclient.databinding.ActivityCreatePostBinding
import kotlinx.coroutines.launch

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding
    private val apiService = JsonPlaceHolderServiceImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPost(true)

        binding.createPostBtn.setOnClickListener {
            createPost()
        }
        binding.getPostBtn.setOnClickListener {
            Intent(this@CreatePostActivity, SocketActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

    private fun createPost(){
        binding.apply {
            val userIdText = userIdEdt.text.toString().trim()
            val bodyText = bodyEdt.text.toString().trim()
            val titleText = titleEdt.text.toString().trim()

            if (userIdText.isEmpty()){
                userIdBox.error = "ID cannot be empty"
                return
            }

            if (titleText.isEmpty()) {
                titleBox.error = "Title cannot be empty"
                return
            }

            val post = PostRequestModel(
                userId = userIdText.toInt(),
                title = titleText,
                body = bodyText
            )

            lifecycleScope.launch {
                val response = apiService.createPost(post)
                response?.let {
                    Toast.makeText(this@CreatePostActivity, "Post created ${it.userId}", Toast.LENGTH_SHORT)
                        .show()
                }?: Toast.makeText(this@CreatePostActivity, "Oops ... Post Not created", Toast.LENGTH_SHORT)
                    .show()
            }



        }
    }

    private fun getPost(initialState:Boolean){
        binding.apply {
            val userIdText = userIdEdt.text.toString().trim()


            if (userIdText.isEmpty()){
                userIdBox.error = "ID cannot be empty"
                return
            }

            binding.emptyStateTv.text = if (initialState) "Tap to view posts"  else "Unable to get post (Check log in dev)"

            if (initialState){
                return
            }
            lifecycleScope.launch {
                val response = apiService.getPost(userIdText.toInt())
                postTextLayout.root.isVisible = response != null
                response?.let {
                    postTextLayout.titleText.text = it.title.ifEmpty { "--- ---" }
                    postTextLayout.bodyText.text = it.body.ifEmpty { "--- ---" }
                    Toast.makeText(this@CreatePostActivity, "Post retrieved ${it.userId}", Toast.LENGTH_SHORT)
                        .show()
                }?: Toast.makeText(this@CreatePostActivity, "Oops ... Post Not created", Toast.LENGTH_SHORT)
                    .show()
            }



        }
    }
}
package com.tutorials.ktorclient.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tutorials.ktorclient.data.remote.JsonPlaceHolderServiceImpl
import com.tutorials.ktorclient.data.remote.dto.PostResponseModel
import com.tutorials.ktorclient.databinding.ActivityMainBinding
import com.tutorials.ktorclient.ui.adapter.PostsAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiService = JsonPlaceHolderServiceImpl()
    private val postAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter(true,emptyList())
        binding.postsBtn.setOnClickListener{
            lifecycleScope.launch {
                val posts = apiService.getPosts()
                setUpAdapter(false,posts)
            }

        }

        binding.createBtn.setOnClickListener {
            Intent(this@MainActivity, CreatePostActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun setUpAdapter(initialState:Boolean,postList:List<PostResponseModel>){
        binding.imagesRv.adapter = postAdapter
        binding.imagesRv.isVisible = postList.isNotEmpty()
        binding.emptyStateTv.isVisible = postList.isEmpty()
        binding.emptyStateTv.text = if (initialState) "Tap to view all posts" else "Unable to get post (Check log in dev)"
        postAdapter.submitList(postList)


    }
}
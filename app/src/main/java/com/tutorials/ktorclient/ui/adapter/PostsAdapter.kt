package com.tutorials.ktorclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.ktorclient.R
import com.tutorials.ktorclient.data.remote.dto.PostResponseModel
import com.tutorials.ktorclient.databinding.TextViewHolderBinding

class PostsAdapter : ListAdapter<PostResponseModel, PostsAdapter.ViewHolder>(
    diffObject
) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TextViewHolderBinding.bind(view)

        fun bind(post: PostResponseModel) {
            binding.apply {
                titleText.text = post.title.ifEmpty { "--- ---" }
                bodyText.text = post.body.ifEmpty { "--- ---" }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.text_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = getItem(position)
        if (pos != null)
            holder.bind(pos)

    }

    companion object {
        val diffObject = object : DiffUtil.ItemCallback<PostResponseModel>() {
            override fun areItemsTheSame(oldItem: PostResponseModel, newItem: PostResponseModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: PostResponseModel, newItem: PostResponseModel): Boolean {
                return oldItem.title == newItem.title && oldItem.body == newItem.body
            }
        }
    }

}
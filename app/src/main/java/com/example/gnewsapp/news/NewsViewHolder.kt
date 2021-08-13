package com.example.gnewsapp.news

import android.view.View
import com.bumptech.glide.RequestManager
import com.example.gnewsapp.adapter.BaseViewHolder
import com.example.gnewsapp.databinding.ItemNewsBinding

class NewsViewHolder(
    itemView: View,
    private val onClick: (NewsItem) -> Unit,
    private val requestManager: RequestManager
) : BaseViewHolder<NewsItem>(itemView) {
    private val binding = ItemNewsBinding.bind(itemView)

    init {
        itemView.setOnClickListener { onClick(item!!) }
    }

    override fun onBind(item: NewsItem) {
        requestManager.clear(binding.articleImage)
        requestManager.load(item.image).dontTransform()
            .into(binding.articleImage)
        binding.articleTitle.text = item.title
        binding.articleDescription.text = item.description
    }
}

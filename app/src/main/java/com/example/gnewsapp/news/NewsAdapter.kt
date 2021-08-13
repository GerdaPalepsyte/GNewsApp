package com.example.gnewsapp.news

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.gnewsapp.R
import com.example.gnewsapp.adapter.BaseAdapter
import com.example.gnewsapp.adapter.BaseViewHolder

class NewsAdapter(private val onClick: (NewsItem) -> Unit, private val requestManager: RequestManager) :
    BaseAdapter<NewsItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NewsItem> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view, onClick, requestManager)
    }
}
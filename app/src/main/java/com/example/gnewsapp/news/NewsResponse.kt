package com.example.gnewsapp.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("articles") val articles: List<ApiNews>)

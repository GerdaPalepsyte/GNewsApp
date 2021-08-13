package com.example.gnewsapp.news

import com.google.gson.annotations.SerializedName

data class ApiNews(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String?,
    @SerializedName("url") val url: String
)

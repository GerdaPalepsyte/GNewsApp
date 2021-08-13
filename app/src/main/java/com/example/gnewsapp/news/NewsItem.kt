package com.example.gnewsapp.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val title: String,
    val description: String,
    val image: String?,
    val url: String
) : Parcelable

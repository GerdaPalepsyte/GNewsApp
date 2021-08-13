package com.example.gnewsapp.news

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NewsApiService {
    @GET("https://gnews.io/api/v4/top-headlines?token=88c341f555cc47dd21cd106591ca4720")
    fun loadNews(): Single<NewsResponse>
}
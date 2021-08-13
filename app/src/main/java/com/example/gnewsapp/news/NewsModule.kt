package com.example.gnewsapp.news

import com.example.gnewsapp.database.NewsDao
import com.example.gnewsapp.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object NewsModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

    @Provides
    fun provideSourceDao(database: NewsDatabase): NewsDao =
        database.newsDao()
}

package com.example.gnewsapp.news

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdateNewsUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
    private val repository: NewsRepository,
    private val mapper: NewsMapper
) {
    fun updateNews(): Completable {
        return newsApiService.loadNews()
            .flatMapCompletable { response ->
                repository.insert(mapper.mapToNews(response.articles))
            }
    }
}
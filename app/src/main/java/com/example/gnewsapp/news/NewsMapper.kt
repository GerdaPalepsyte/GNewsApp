package com.example.gnewsapp.news

import com.example.gnewsapp.database.DbNews
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    fun mapToItem(dbItems: List<DbNews>): List<NewsItem> {
        return dbItems.map { dbNews ->
            toNewsItem(dbNews)
        }
    }

    fun mapToNews(apiNews: List<ApiNews>): List<DbNews> {
        return apiNews.map { articles ->
            toDbNews(articles)
        }
    }

    private fun toNewsItem(
        dbNews: DbNews,
    ) = NewsItem(
        dbNews.title,
        dbNews.description,
        dbNews.image.orEmpty(),
        dbNews.url
    )

    private fun toDbNews(
        apiNews: ApiNews,
    ) = DbNews(
        apiNews.title,
        apiNews.description,
        apiNews.image.orEmpty(),
        apiNews.url
    )
}
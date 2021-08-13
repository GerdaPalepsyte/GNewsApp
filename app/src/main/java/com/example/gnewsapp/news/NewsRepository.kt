package com.example.gnewsapp.news

import com.example.gnewsapp.database.DbNews
import com.example.gnewsapp.database.NewsDao
import com.example.gnewsapp.database.RepositoryExecutor
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val executor: RepositoryExecutor,
    private val mapper: NewsMapper
) {
    fun observeNews(): Observable<List<NewsItem>> {
        return executor.observe { newsDao.observeNews() }
            .map { dbNews ->
                mapper.mapToItem(dbNews)
            }
    }

    fun insert(news: List<DbNews>): Completable =
        executor.completable { newsDao.insertAll(news) }
}

package com.example.gnewsapp.database

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

class RepositoryExecutor {
    private var scheduler = Schedulers.from(
        Executors.newSingleThreadExecutor { runnable -> Thread(runnable, "Database") }
    )

    fun <T> observe(supplier: Supplier<Observable<T>>): Observable<T> =
        Observable.defer(supplier).subscribeOn(scheduler)

    fun completable(runnable: Runnable): Completable =
        Completable.fromRunnable(runnable).subscribeOn(scheduler)
}
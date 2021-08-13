package com.example.gnewsapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gnewsapp.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val updateNewsUseCase: UpdateNewsUseCase,
    private val repository: NewsRepository
) : ViewModel() {
    private val itemsLiveData = MutableLiveData<List<NewsItem>>()
    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = LiveEvent<Throwable>()
    private val selectedItemLiveEvent = LiveEvent<NewsItem>()

    private var disposable = Disposable.disposed()
    private var observeDisposable = Disposable.disposed()

    fun itemsLiveData(): LiveData<List<NewsItem>> = itemsLiveData

    fun progressLiveData(): LiveData<Boolean> = showProgressLiveData

    fun errorLiveData(): LiveData<Throwable> = errorLiveData

    fun selectedItemLiveData(): LiveData<NewsItem> = selectedItemLiveEvent

    fun onItemSelected(item: NewsItem) {
        selectedItemLiveEvent.value = item
    }

    fun onCreated() {
        loadSources()
        observeSources()
    }

    private fun observeSources() {
        if (!observeDisposable.isDisposed) return
        observeDisposable = repository.observeNews()
            .subscribe { newsItems ->
                itemsLiveData.postValue(newsItems)
            }
    }

    fun onRefresh() {
        loadSources()
    }

    private fun loadSources() {
        if (!disposable.isDisposed) return
        showProgressLiveData.value = true
        disposable = updateNewsUseCase.updateNews()
            .subscribe(
                {
                    showProgressLiveData.postValue(false)
                },
                { cause: Throwable ->
                    Timber.e(cause)
                    showProgressLiveData.postValue(false)
                    errorLiveData.postValue(cause)
                }
            )
    }

    override fun onCleared() {
        disposable.dispose()
        observeDisposable.dispose()
    }
}

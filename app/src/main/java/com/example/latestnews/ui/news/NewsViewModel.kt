package com.example.latestnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.latestnews.model.remote.Article
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * News view model
 *
 * @property newsRepository [NewsRepository]
 */
class NewsViewModel(private val newsRepository: NewsRepository) :
        BaseViewModel() {

    // initialization time call to fetch the matches which should not be called
    // on configuration changes like device rotation
    init {
        fetchTopNews()
    }

    /**
     * to make network call on particular user action like pull to refresh or
     * for periodic update from the web service we can use this function
     *
     */
    fun fetchTopNews() {
        viewModelScope.launch {
            newsRepository.fetchTopNews(10, viewModelScope)
        }
    }


    /**
     * Subscribe to paging data source
     *
     */
    fun subscribeToPagingData(): LiveData<PagingData<Article>> {
        return newsRepository.flow.asLiveData()
    }
}
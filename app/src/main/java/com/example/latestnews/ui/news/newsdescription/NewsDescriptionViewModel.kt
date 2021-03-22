package com.example.latestnews.ui.news.newsdescription

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.util.SingleLiveEvent
import com.example.latestnews.model.local.NewsAdditionalInfo
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsDescriptionViewModel(
    private val newsRepository: NewsRepository,
    private val io: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {


    val likesCount = SingleLiveEvent<String>()
    val commentsCount = SingleLiveEvent<String>()
    val groupVisibility = MutableLiveData<Boolean>(false)

    fun fetchAdditionalInfo(articleId: String) {
        viewModelScope.launch {
            newsRepository.fetchComments(articleId)
                .zip(newsRepository.fetchLikes(articleId)) { t1, t2 ->
                    NewsAdditionalInfo(t1.comments, t2.likes)
                }
                .flowOn(io)
                .catch { Timber.e(it) } // TODO error handling
                .collectLatest {
                    Timber.d(it.toString())
                    likesCount.postValue(it.likes.toString())
                    commentsCount.postValue(it.comments.toString())
                    groupVisibility.postValue(true)
                }
        }
    }
}
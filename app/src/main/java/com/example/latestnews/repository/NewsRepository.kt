package com.example.latestnews.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.latestnews.model.remote.Article
import com.example.latestnews.model.remote.newsadditionalinfo.CommentsResponse
import com.example.latestnews.model.remote.newsadditionalinfo.LikesResponse
import com.example.latestnews.networking.AdditionalInfoApiService
import com.example.latestnews.networking.NewsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf

class NewsRepository(
        private val newsApiService: NewsApiService,
        private val additionalInfoApiService: AdditionalInfoApiService
) {

    val flow = MutableSharedFlow<PagingData<Article>>(replay = 1)

    suspend fun fetchTopNews(pageSize: Int, viewModelScope: CoroutineScope) {
        Pager(
                PagingConfig(
                        pageSize = pageSize,
                        enablePlaceholders = false
                )
        )
        {
            PageKeyedTopNewsPagingSource(
                    newsApiService = newsApiService,
                    pageSize
            )
        }.flow.cachedIn(viewModelScope).collectLatest {
            flow.emit(it)
        }
    }

    suspend fun fetchComments(articleId: String): Flow<CommentsResponse> {
        return flowOf(additionalInfoApiService.fetchComments(articleId))
    }

    suspend fun fetchLikes(articleId: String): Flow<LikesResponse> {
        return flowOf(additionalInfoApiService.fetchLikes(articleId))
    }

}

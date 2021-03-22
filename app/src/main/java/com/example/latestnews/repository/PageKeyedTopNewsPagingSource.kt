package com.example.latestnews.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.latestnews.BuildConfig
import com.example.latestnews.model.remote.Article
import com.example.latestnews.networking.NewsApiService
import retrofit2.HttpException
import java.io.IOException

class PageKeyedTopNewsPagingSource(
    private val newsApiService: NewsApiService,
    private val perPage: Int
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val newsResponse = newsApiService.fetchNews(
                apiKey = BuildConfig.API_KEY,
                country = "us",
                page = nextPage,
                perPage = perPage
            )

            LoadResult.Page(
                data = newsResponse.articles,
                prevKey = null, // only paging forward
                nextKey = if (nextPage * perPage <= newsResponse.totalResults) nextPage.plus(1) else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
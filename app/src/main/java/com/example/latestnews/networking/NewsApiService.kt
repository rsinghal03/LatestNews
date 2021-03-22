package com.example.latestnews.networking

import com.example.latestnews.model.remote.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Example Apis

// Likes: https://cn-news-info-api.herokuapp.com/likes/news.yahoo.com-uganda-lions-found-dead-mutilated-155636332.html?guccounter=1
// Comments: https://cn-news-info-api.herokuapp.com/comments/news.yahoo.com-uganda-lions-found-dead-mutilated-155636332.html?guccounter=1

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun fetchNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): NewsApiResponse

}
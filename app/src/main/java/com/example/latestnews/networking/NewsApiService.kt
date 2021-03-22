package com.example.latestnews.networking

import com.example.latestnews.model.remote.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun fetchNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): NewsApiResponse

}
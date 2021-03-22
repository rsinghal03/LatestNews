package com.example.latestnews.networking

import com.example.latestnews.model.remote.newsadditionalinfo.CommentsResponse
import com.example.latestnews.model.remote.newsadditionalinfo.LikesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AdditionalInfoApiService {

    @GET("likes/{article_id}")
    suspend fun fetchLikes(
        @Path("article_id") articleId: String
    ): LikesResponse

    @GET("comments/{article_id}")
    suspend fun fetchComments(
        @Path("article_id") articleId: String
    ): CommentsResponse
}
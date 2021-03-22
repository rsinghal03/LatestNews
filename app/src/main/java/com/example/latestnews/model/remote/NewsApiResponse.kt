package com.example.latestnews.model.remote

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
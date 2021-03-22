package com.example.latestnews.di

import com.example.latestnews.networking.AdditionalInfoApiService
import com.example.latestnews.networking.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { getNewsApiService() }
    single { getAdditionalInfoApiService() }
}

private fun getNewsApiService(): NewsApiService {
    return createRetrofit(NEWS_API_BASE_URL).create(NewsApiService::class.java)
}

private fun getAdditionalInfoApiService(): AdditionalInfoApiService {
    return createRetrofit(ADDITIONAL_INFO_API_BASE_URL).create(AdditionalInfoApiService::class.java)
}

private fun createRetrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
}

private fun getOkHttpClient(): OkHttpClient {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

private const val NEWS_API_BASE_URL = "https://newsapi.org/"
private const val ADDITIONAL_INFO_API_BASE_URL = "https://cn-news-info-api.herokuapp.com/"
private const val READ_WRITE_TIMEOUT: Long = 60
private const val CONNECTION_TIMEOUT: Long = 60

//https://newsapi.org/v2/top-headlines?country=us&apiKey=5151765c56a64778a72adcacfb409582&pageSize=10&page=4
// Likes: https://cn-news-info-api.herokuapp.com/likes/news.yahoo.com-uganda-lions-found-dead-mutilated-155636332.html?guccounter=1
// Comments: https://cn-news-info-api.herokuapp.com/comments/news.yahoo.com-uganda-lions-found-dead-mutilated-155636332.html?guccounter=1

package com.architect.mynewsapp.network

import com.architect.mynewsapp.models.NewsResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
        @Query("page") page: Int
    ): NewsResponse
}



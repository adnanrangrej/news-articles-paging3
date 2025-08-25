package com.github.adnanrangrej.data.remote

import com.github.adnanrangrej.data.remote.models.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Header("X-Api-Key") apiKey: String = "0e883e06ba544c7aaee9c2dd8ee66f78"
    ) : NewsApiResponse
}
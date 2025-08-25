package com.github.adnanrangrej.data.remote.models


import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
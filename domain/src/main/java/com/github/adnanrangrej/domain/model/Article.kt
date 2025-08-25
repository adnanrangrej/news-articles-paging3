package com.github.adnanrangrej.domain.model

data class Article(
    val publishedAt: String,
    val author: String,
    val content: String,
    val description: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val imageUrl: String
)
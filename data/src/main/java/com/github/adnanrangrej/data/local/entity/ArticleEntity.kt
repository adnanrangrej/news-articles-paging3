package com.github.adnanrangrej.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val publishedAt: String,
    val author: String,
    val content: String,
    val description: String,
    val sourceName: String,
    val title: String,
    val imageUrl: String
)
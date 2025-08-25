package com.github.adnanrangrej.data.local

import com.github.adnanrangrej.data.local.entity.ArticleEntity
import com.github.adnanrangrej.data.remote.models.ArticleDto
import com.github.adnanrangrej.domain.model.Article

fun ArticleDto.toArticleEntity() = ArticleEntity(
    url = url,
    publishedAt = publishedAt,
    author = author,
    content = content,
    description = description,
    sourceName = source.name,
    title = title,
    imageUrl = urlToImage
)

fun ArticleEntity.toDomainModel() = Article(
    publishedAt = publishedAt,
    author = author,
    content = content,
    description = description,
    sourceName = sourceName,
    title = title,
    url = url,
    imageUrl = imageUrl
)
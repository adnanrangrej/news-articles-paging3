package com.github.adnanrangrej.domain.repository

import androidx.paging.PagingData
import com.github.adnanrangrej.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsArticles(): Flow<PagingData<Article>>

    fun getNewsArticleByaArticleUrl(articleUrl: String): Flow<Article?>
}
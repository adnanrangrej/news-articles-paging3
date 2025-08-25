package com.github.adnanrangrej.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.github.adnanrangrej.data.local.AppDatabase
import com.github.adnanrangrej.data.local.toDomainModel
import com.github.adnanrangrej.data.paging.NewsRemoteMediator
import com.github.adnanrangrej.data.remote.NewsApiService
import com.github.adnanrangrej.domain.model.Article
import com.github.adnanrangrej.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val apiService: NewsApiService
) : NewsRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getNewsArticles(): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { database.articleDao().getPagingSource() },
            remoteMediator = NewsRemoteMediator(
                database = database,
                apiService = apiService
            )
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override fun getNewsArticleByaArticleUrl(articleUrl: String): Flow<Article?> {
        return database.articleDao().getNewsArticleByArticleUrl(articleUrl).map {
            it?.toDomainModel()
        }
    }
}
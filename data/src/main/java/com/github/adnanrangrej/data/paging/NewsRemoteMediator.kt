package com.github.adnanrangrej.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.github.adnanrangrej.data.local.AppDatabase
import com.github.adnanrangrej.data.local.entity.ArticleEntity
import com.github.adnanrangrej.data.local.entity.RemoteKey
import com.github.adnanrangrej.data.local.toArticleEntity
import com.github.adnanrangrej.data.remote.NewsApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    val database: AppDatabase,
    val apiService: NewsApiService
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val remoteKey = getLastRemoteKey(state)
                    if (remoteKey == null) {
                        return MediatorResult.Success(true)
                    }
                    remoteKey.nextKey ?: return MediatorResult.Success(true)
                }
            }

            val response = apiService.getArticles(
                query = "android",
                pageSize = state.config.pageSize,
                page = page
            )

            val endOfPaginationReached = response.articles.isEmpty()
            val articles = response.articles.map {
                it.toArticleEntity()
            }

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().clearAll()
                    database.articleDao().clearAll()
                }

                // Calculate the next page and previous page
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                // Remote keys object
                val keys = articles.map {
                    RemoteKey(
                        articleUrl = it.url,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                // Insert the keys to db
                database.remoteKeyDao().insertAllKeys(keys)
                database.articleDao().insertArticles(articles)

            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            if (e.code() == 426) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(e)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ArticleEntity>): RemoteKey? {

        val lastPage = state.pages.lastOrNull { page ->
            page.data.isNotEmpty()
        }

        if (lastPage == null) {
            return null
        }

        val lastItem = lastPage.data.lastOrNull()

        if (lastItem == null) {
            return null
        }

        val remoteKey = database.remoteKeyDao().getRemoteKeyByUrl(lastItem.url)

        return remoteKey
    }
}
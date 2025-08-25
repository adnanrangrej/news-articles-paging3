package com.github.adnanrangrej.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.adnanrangrej.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM article")
    fun getPagingSource(): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM article WHERE url = :articleUrl")
    fun getNewsArticleByArticleUrl(articleUrl: String): Flow<ArticleEntity?>

    @Query("DELETE FROM article")
    suspend fun clearAll()
}
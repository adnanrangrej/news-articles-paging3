package com.github.adnanrangrej.newsarticles.di

import com.github.adnanrangrej.domain.repository.NewsRepository
import com.github.adnanrangrej.domain.usecase.GetNewsArticleByArticleUrlUseCase
import com.github.adnanrangrej.domain.usecase.GetNewsArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun providesGetNewsArticlesUseCase(newsRepository: NewsRepository): GetNewsArticlesUseCase {
        return GetNewsArticlesUseCase(newsRepository)
    }


    @Provides
    @ViewModelScoped
    fun providesGetNewsArticleByArticleUrlUseCase(newsRepository: NewsRepository): GetNewsArticleByArticleUrlUseCase {
        return GetNewsArticleByArticleUrlUseCase(newsRepository)
    }
}
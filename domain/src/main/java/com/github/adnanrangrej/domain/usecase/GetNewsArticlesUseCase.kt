package com.github.adnanrangrej.domain.usecase

import com.github.adnanrangrej.domain.repository.NewsRepository

class GetNewsArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getNewsArticles()
}
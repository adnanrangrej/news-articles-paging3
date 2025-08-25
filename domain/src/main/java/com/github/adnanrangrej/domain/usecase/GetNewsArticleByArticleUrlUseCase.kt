package com.github.adnanrangrej.domain.usecase

import com.github.adnanrangrej.domain.repository.NewsRepository

class GetNewsArticleByArticleUrlUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(articleUrl: String) = newsRepository.getNewsArticleByaArticleUrl(articleUrl)
}
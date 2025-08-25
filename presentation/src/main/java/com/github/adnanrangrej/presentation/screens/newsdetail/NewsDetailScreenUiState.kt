package com.github.adnanrangrej.presentation.screens.newsdetail

import com.github.adnanrangrej.domain.model.Article

sealed interface NewsDetailScreenUiState {

    data class Success(val article: Article) : NewsDetailScreenUiState
    data class Error(val message: String) : NewsDetailScreenUiState
    object Loading : NewsDetailScreenUiState
}
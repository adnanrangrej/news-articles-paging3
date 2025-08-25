package com.github.adnanrangrej.presentation.screens.newsdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.adnanrangrej.domain.usecase.GetNewsArticleByArticleUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class NewsDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    articleUrlUseCase: GetNewsArticleByArticleUrlUseCase
) : ViewModel() {
    private val encodedUrl: String = savedStateHandle.get<String>("articleUrl") ?: ""

    val articleUrl: String = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())

    val uiState: StateFlow<NewsDetailScreenUiState> = articleUrlUseCase(articleUrl).map { article ->
        if (article != null) {
            NewsDetailScreenUiState.Success(article)
        } else {
            NewsDetailScreenUiState.Error("Article with Url: $articleUrl not found")
        }
    }
        .catch { exception ->
            emit(NewsDetailScreenUiState.Error(exception.message ?: "An Unknown Error Occurred"))
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = NewsDetailScreenUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )
}
package com.github.adnanrangrej.presentation.screens.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.github.adnanrangrej.domain.usecase.GetNewsArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsListScreenViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel() {
    val newsFlow = getNewsArticlesUseCase().cachedIn(viewModelScope)

}
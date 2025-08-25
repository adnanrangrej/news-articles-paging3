package com.github.adnanrangrej.presentation.screens.newslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NewsListScreen(
    newsListScreenViewModel: NewsListScreenViewModel = hiltViewModel(),
    navigateToNewsDetails: (String) -> Unit
) {

    val newsPagedList = newsListScreenViewModel.newsFlow.collectAsLazyPagingItems()

    NewsListScreenBody(
        modifier = Modifier.fillMaxSize(),
        newsItem = newsPagedList,
        onNewsItemClick = { article -> navigateToNewsDetails(article.url) }
    )
}
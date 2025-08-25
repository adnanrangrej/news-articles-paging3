package com.github.adnanrangrej.presentation.screens.newslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.github.adnanrangrej.domain.model.Article

@Composable
fun NewsListScreenBody(
    modifier: Modifier = Modifier,
    newsItem: LazyPagingItems<Article>,
    onNewsItemClick: (Article) -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        if (newsItem.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(newsItem.itemCount, key = {
                    newsItem[it]?.url ?: ""
                }) { index ->

                    val article = newsItem[index]

                    if (article != null) {

                        NewsListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { onNewsItemClick(article) }),
                            article = article
                        )
                    }
                }

                item {
                    if (newsItem.loadState.append is LoadState.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
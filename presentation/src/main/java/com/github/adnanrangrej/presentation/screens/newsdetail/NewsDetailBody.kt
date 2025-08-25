package com.github.adnanrangrej.presentation.screens.newsdetail

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun NewsDetailBody(
    modifier: Modifier = Modifier,
    uiState: NewsDetailScreenUiState,
    onRetryClick: () -> Unit,
    onOpenBrowser: (Uri) -> Unit
) {

    when (uiState) {
        is NewsDetailScreenUiState.Error -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = uiState.message, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(16.dp))
                Button(onClick = onRetryClick) {
                    Text("Retry")
                }
            }
        }

        NewsDetailScreenUiState.Loading -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is NewsDetailScreenUiState.Success -> {
            Column(
                modifier = modifier
            ) {
                NewsDetailItem(
                    modifier = Modifier.weight(1f),
                    article = uiState.article
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onOpenBrowser(uiState.article.url.toUri()) },
                    ) {
                        Text(text = "Click here to read full news")
                    }
                }
            }
        }
    }
}
package com.github.adnanrangrej.presentation.screens.newsdetail

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailScreenViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    NewsDetailBody(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState.value,
        onRetryClick = { },
        onOpenBrowser = {
            val intent = Intent(Intent.ACTION_VIEW, it)
            context.startActivity(intent)
        }
    )
}
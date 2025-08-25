package com.github.adnanrangrej.newsarticles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.adnanrangrej.newsarticles.ui.theme.NewsArticlesTheme
import com.github.adnanrangrej.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsArticlesTheme {
                AppNavigation()
            }
        }
    }
}

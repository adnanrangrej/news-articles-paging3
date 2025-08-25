package com.github.adnanrangrej.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.adnanrangrej.presentation.component.NewsAppTopAppBar
import com.github.adnanrangrej.presentation.navigation.destination.AppNavigationDestination
import com.github.adnanrangrej.presentation.screens.newsdetail.NewsDetailScreen
import com.github.adnanrangrej.presentation.screens.newslist.NewsListScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = currentBackStackEntry?.destination?.route

    val currentDestination =
        when {
            currentRoute == AppNavigationDestination.NewsList.route -> AppNavigationDestination.NewsList
            currentRoute?.startsWith(AppNavigationDestination.NewsDetails.route) == true -> AppNavigationDestination.NewsDetails
            else -> null
        }

    Scaffold(
        topBar = {
            if (currentDestination != null) {
                NewsAppTopAppBar(
                    title = currentDestination.title,
                    canNavigateUp = currentDestination.canNavigateBack,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppNavigationDestination.NewsList.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = AppNavigationDestination.NewsList.route) {
                NewsListScreen { articleUrl ->
                    val encodedUrl =
                        URLEncoder.encode(articleUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        AppNavigationDestination.NewsDetails.createRoute(
                            encodedUrl
                        )
                    )
                }
            }

            composable(
                route = AppNavigationDestination.NewsDetails.routeWithArgs,
                arguments = listOf(navArgument(AppNavigationDestination.NewsDetails.ARTICLE_URL) {
                    type = NavType.StringType
                })
            ) {
                NewsDetailScreen()
            }
        }


    }
}
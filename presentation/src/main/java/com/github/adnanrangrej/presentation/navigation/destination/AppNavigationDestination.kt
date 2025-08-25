package com.github.adnanrangrej.presentation.navigation.destination

sealed interface AppNavigationDestination {

    val route: String
    val canNavigateBack: Boolean
    val title: String

    data object NewsList : AppNavigationDestination {
        override val route: String = "news_list"
        override val canNavigateBack: Boolean = false
        override val title: String = "Top Android Headlines"
    }

    data object NewsDetails : AppNavigationDestination {
        override val route: String = "news_details"
        override val canNavigateBack: Boolean = true
        override val title: String = "Article Details"
        const val ARTICLE_URL = "articleUrl"
        val routeWithArgs = "$route/{$ARTICLE_URL}"
        fun createRoute(articleUrl: String) = "$route/$articleUrl"
    }
}
package com.alex.yang.alexunsplashpagingcompose.navigation

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */
sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")
}

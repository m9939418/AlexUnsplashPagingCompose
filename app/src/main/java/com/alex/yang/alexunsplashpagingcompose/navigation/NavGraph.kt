package com.alex.yang.alexunsplashpagingcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alex.yang.alexunsplashpagingcompose.home.presentation.HomeScreen
import com.alex.yang.alexunsplashpagingcompose.search.presentation.SearchScreen

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navHostController)
        }

        composable(route = Screen.Search.route) {
            SearchScreen(navHostController)
        }
    }
}
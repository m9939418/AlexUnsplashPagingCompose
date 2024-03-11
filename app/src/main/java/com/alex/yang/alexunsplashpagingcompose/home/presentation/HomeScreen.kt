package com.alex.yang.alexunsplashpagingcompose.home.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alex.yang.alexunsplashpagingcompose.home.presentation.component.HomeTopBar
import com.alex.yang.alexunsplashpagingcompose.home.presentation.component.ListContents
import com.alex.yang.alexunsplashpagingcompose.navigation.Screen

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navHostController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            ListContents(pagingItems = getAllImages)
        }
    )
}
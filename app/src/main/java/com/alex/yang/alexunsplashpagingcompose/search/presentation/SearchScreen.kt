package com.alex.yang.alexunsplashpagingcompose.search.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alex.yang.alexunsplashpagingcompose.home.presentation.component.ListContents
import com.alex.yang.alexunsplashpagingcompose.search.presentation.component.SearchWidget

/**
 * Created by AlexYang on 2024/3/11.
 *
 *
 */

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchImages = searchViewModel.searchImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        },
        content = {
            ListContents(pagingItems = searchImages)
        }
    )
}
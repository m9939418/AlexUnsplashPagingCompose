package com.alex.yang.alexunsplashpagingcompose.home.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alex.yang.alexunsplashpagingcompose.ui.theme.topAppBarBackgroundColor
import com.alex.yang.alexunsplashpagingcompose.ui.theme.topAppBarContentColor

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */
@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Home",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar {}
}
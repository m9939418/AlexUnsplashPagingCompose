package com.alex.yang.alexunsplashpagingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alex.yang.alexunsplashpagingcompose.ui.theme.AlexUnsplashPagingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlexUnsplashPagingComposeTheme {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlexUnsplashPagingComposeTheme {

    }
}
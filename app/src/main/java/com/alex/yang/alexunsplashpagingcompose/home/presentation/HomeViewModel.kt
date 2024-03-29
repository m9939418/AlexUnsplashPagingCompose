package com.alex.yang.alexunsplashpagingcompose.home.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.alex.yang.alexunsplashpagingcompose.home.data.mapper.toUnsplashImage
import com.alex.yang.alexunsplashpagingcompose.home.data.repository.UnsplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: UnsplashRepositoryImpl,
) : ViewModel() {
    val getAllImages = repository.getAllImages().map { pagingData ->
        pagingData.map { it.toUnsplashImage() }
    }
}
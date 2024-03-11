package com.alex.yang.alexunsplashpagingcompose.home.domain.repository

import androidx.paging.PagingData
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by AlexYang on 2024/3/8.
 *
 *
 */
interface IUnsplashRepository {
    fun getAllImages(): Flow<PagingData<ResultEntity>>
}
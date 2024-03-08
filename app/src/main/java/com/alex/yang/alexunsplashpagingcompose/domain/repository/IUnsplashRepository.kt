package com.alex.yang.alexunsplashpagingcompose.domain.repository

import androidx.paging.PagingData
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by AlexYang on 2024/3/8.
 *
 *
 */
interface IUnsplashRepository {
    fun getAllImages(): Flow<PagingData<ResultEntity>>
}
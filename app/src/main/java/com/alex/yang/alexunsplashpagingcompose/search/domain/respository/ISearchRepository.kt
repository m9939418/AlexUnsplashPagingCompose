package com.alex.yang.alexunsplashpagingcompose.search.domain.respository

import androidx.paging.PagingData
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by AlexYang on 2024/3/9.
 *
 *
 */
interface ISearchRepository {
    fun searchImages(query: String): Flow<PagingData<ResultEntity>>
}
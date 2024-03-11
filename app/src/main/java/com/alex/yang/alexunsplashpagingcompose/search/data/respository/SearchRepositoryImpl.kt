package com.alex.yang.alexunsplashpagingcompose.search.data.respository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alex.yang.alexunsplashpagingcompose.core.data.api.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.search.data.remote.SearchPagingSource
import com.alex.yang.alexunsplashpagingcompose.search.domain.respository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/9.
 *
 *
 */
class SearchRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val database: ResultDatabase,
) : ISearchRepository {

    override fun searchImages(query: String): Flow<PagingData<ResultEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = api, query = query)
            }
        ).flow
    }
}
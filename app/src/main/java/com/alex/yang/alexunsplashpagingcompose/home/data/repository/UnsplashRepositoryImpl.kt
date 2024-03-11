package com.alex.yang.alexunsplashpagingcompose.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alex.yang.alexunsplashpagingcompose.core.data.api.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.remote.UnsplashRemoteMediator
import com.alex.yang.alexunsplashpagingcompose.home.domain.repository.IUnsplashRepository
import com.alex.yang.alexunsplashpagingcompose.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/3.
 *
 *
 */
@OptIn(ExperimentalPagingApi::class)
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val resultDatabase: ResultDatabase,
) : IUnsplashRepository {

    override fun getAllImages(): Flow<PagingData<ResultEntity>> {
        val pagingSourceFactory = { resultDatabase.resultDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                resultDatabase = resultDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
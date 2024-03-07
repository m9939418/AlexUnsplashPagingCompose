package com.alex.yang.alexunsplashpagingcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.paging.util.INITIAL_ITEM_COUNT
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.remote.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.data.paging.UnsplashRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/3.
 *
 *
 */
@OptIn(ExperimentalPagingApi::class)
class UnsplashRepository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val resultDatabase: ResultDatabase,
) {

    fun getAllImages(): Flow<PagingData<ResultEntity>> {
        val pagingSourceFactory = { resultDatabase.resultDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = INITIAL_ITEM_COUNT),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                resultDatabase = resultDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
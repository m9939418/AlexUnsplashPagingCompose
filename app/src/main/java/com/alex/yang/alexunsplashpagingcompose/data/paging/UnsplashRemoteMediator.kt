package com.alex.yang.alexunsplashpagingcompose.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultRemoteKeysEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.remote.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.data.mapper.toResultEntity
import com.alex.yang.alexunsplashpagingcompose.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/3.
 *
 *
 */
@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val resultDatabase: ResultDatabase,
) : RemoteMediator<Int, ResultEntity>() {
    private val resultDao = resultDatabase.resultDao()
    private val resultRemoteKeysDao = resultDatabase.resultRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = unsplashApi
                .getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
                .map { it.toResultEntity() }
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            resultDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    resultDao.deleteAllImages()
                    resultRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map {
                    ResultRemoteKeysEntity(
                        id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                resultRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                resultDao.addImages(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ResultEntity>
    ): ResultRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                resultRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ResultEntity>
    ): ResultRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { entity ->
                resultRemoteKeysDao.getRemoteKeys(id = entity.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ResultEntity>
    ): ResultRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { entity ->
                resultRemoteKeysDao.getRemoteKeys(id = entity.id)
            }
    }
}
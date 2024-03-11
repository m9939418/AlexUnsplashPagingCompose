package com.alex.yang.alexunsplashpagingcompose.home.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.alex.yang.alexunsplashpagingcompose.core.data.api.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultRemoteKeysEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.mapper.toResultEntity
import com.alex.yang.alexunsplashpagingcompose.util.Constants.ITEMS_PER_PAGE

/**
 * Created by AlexYang on 2024/3/3.
 *
 *
 */
@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator (
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

            val response = unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            resultDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    resultDao.deleteAllImages()
                    resultRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { dto ->
                    ResultRemoteKeysEntity(
                        id = dto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                resultRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                resultDao.addImages(images = response.map { it.toResultEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("TAG", "載入數據時發生錯誤", e)
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
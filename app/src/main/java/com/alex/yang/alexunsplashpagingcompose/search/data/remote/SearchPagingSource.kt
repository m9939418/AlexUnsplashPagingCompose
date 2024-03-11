package com.alex.yang.alexunsplashpagingcompose.search.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alex.yang.alexunsplashpagingcompose.data.api.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.data.mapper.toResultEntity

/**
 * Created by AlexYang on 2024/3/9.
 *
 *
 */
class SearchPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String,
) : PagingSource<Int, ResultEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ResultEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.searchImages(query = query, perPage = 10)
            val endOfPaginationReached = response.images.isEmpty()
            if (response.images.isNotEmpty()) {
                return LoadResult.Page(
                    data = response.images.map { it.toResultEntity() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
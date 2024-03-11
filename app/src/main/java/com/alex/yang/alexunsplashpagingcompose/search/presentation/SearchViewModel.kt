package com.alex.yang.alexunsplashpagingcompose.search.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.alex.yang.alexunsplashpagingcompose.home.data.mapper.toUnsplashImage
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.UnsplashImage
import com.alex.yang.alexunsplashpagingcompose.search.domain.respository.ISearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AlexYang on 2024/3/11.
 *
 *
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ISearchRepository
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchImages = _searchImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            repository.searchImages(query = query)
                .map { pagingData -> pagingData.map { it.toUnsplashImage() } }
                .cachedIn(viewModelScope)
                .collect {
                    _searchImages.value = it
                }
        }
    }
}
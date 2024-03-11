package com.alex.yang.alexunsplashpagingcompose.search.data.remote

import com.alex.yang.alexunsplashpagingcompose.home.data.remote.ResultDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AlexYang on 2024/3/9.
 *
 *
 */
@Serializable
data class SearchResultDto(
    @SerialName("results")
    val images: List<ResultDto>
)

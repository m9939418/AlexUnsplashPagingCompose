package com.alex.yang.alexunsplashpagingcompose.data.datasource.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
@Serializable
data class ResultDto(
    val id: String,
    val urls: UrlsDto,
    val likes: Int,
    val user: UserDto
)

@Serializable
data class UrlsDto(
    val regular: String,
)

@Serializable
data class UserDto(
    @SerialName("links")
    val userLinks: UserLinksDto,
    val username: String,
)

@Serializable
data class UserLinksDto(
    val html: String,
)
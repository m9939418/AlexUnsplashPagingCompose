package com.alex.yang.alexunsplashpagingcompose.domain.model

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
data class UnsplashImage(
    val id: String,
    val urls: Urls,
    val likes: Int,
    val user: User
)

data class Urls(
    val regular: String
)

data class User(
    val userLinks: UserLinks,
    val username: String
)

data class UserLinks(
    val html: String
)
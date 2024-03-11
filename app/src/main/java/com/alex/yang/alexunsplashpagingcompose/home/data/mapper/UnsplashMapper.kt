package com.alex.yang.alexunsplashpagingcompose.home.data.mapper

import com.alex.yang.alexunsplashpagingcompose.home.data.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.local.UrlsEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.local.UserEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.local.UserLinksEntity
import com.alex.yang.alexunsplashpagingcompose.home.data.remote.ResultDto
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.UnsplashImage
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.Urls
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.User
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.UserLinks

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
fun ResultDto.toResultEntity(): ResultEntity {
    return ResultEntity(
        id = id,
        urls = UrlsEntity(regular = urls.regular),
        likes = likes,
        user = UserEntity(
            userLinks = UserLinksEntity(html = user.userLinks.html),
            username = user.username
        )
    )
}

fun ResultEntity.toUnsplashImage(): UnsplashImage {
    return UnsplashImage(
        id = id,
        urls = Urls(regular = urls.regular),
        likes = likes,
        user = User(
            userLinks = UserLinks(html = user.userLinks.html),
            username = user.username
        )
    )
}
package com.alex.yang.alexunsplashpagingcompose.data.mapper

import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.UrlsEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.UserEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.UserLinksEntity
import com.alex.yang.alexunsplashpagingcompose.data.datasource.remote.ResultDto
import com.alex.yang.alexunsplashpagingcompose.domain.model.UnsplashImage
import com.alex.yang.alexunsplashpagingcompose.domain.model.Urls
import com.alex.yang.alexunsplashpagingcompose.domain.model.User
import com.alex.yang.alexunsplashpagingcompose.domain.model.UserLinks

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
package com.alex.yang.alexunsplashpagingcompose.home.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex.yang.alexunsplashpagingcompose.util.Constants.UNSPLASH_IMAGE_TABLE

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class ResultEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val urls: UrlsEntity,
    val likes: Int,
    @Embedded
    val user: UserEntity
)

data class UrlsEntity(
    val regular: String,
)

data class UserEntity(
    @Embedded
    val userLinks: UserLinksEntity,
    val username: String,
)

data class UserLinksEntity(
    val html: String,
)
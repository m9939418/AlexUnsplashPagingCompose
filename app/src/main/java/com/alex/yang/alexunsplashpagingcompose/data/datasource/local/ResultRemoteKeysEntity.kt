package com.alex.yang.alexunsplashpagingcompose.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex.yang.alexunsplashpagingcompose.util.Constants.UNSPLASH_REMOTE_KEY_TABLE

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
@Entity(tableName = UNSPLASH_REMOTE_KEY_TABLE)
data class ResultRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?,
)
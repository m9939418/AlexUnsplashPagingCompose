package com.alex.yang.alexunsplashpagingcompose.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by AlexYang on 2024/3/1.
 *
 *
 */
@Database(
    entities = [
        ResultEntity::class,
        ResultRemoteKeysEntity::class
    ],
    version = 1
)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    abstract fun resultRemoteKeysDao(): ResultRemoteKeysDao
}
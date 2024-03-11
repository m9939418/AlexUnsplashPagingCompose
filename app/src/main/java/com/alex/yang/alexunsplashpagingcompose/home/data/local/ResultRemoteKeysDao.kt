package com.alex.yang.alexunsplashpagingcompose.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by AlexYang on 2024/3/1.
 *
 *
 */
@Dao
interface ResultRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_key_table WHERE id=:id")
    suspend fun getRemoteKeys(id: String): ResultRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ResultRemoteKeysEntity>)

    @Query("DELETE FROM unsplash_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}
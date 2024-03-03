package com.alex.yang.alexunsplashpagingcompose.data.datasource.local

import androidx.paging.PagingSource
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
interface ResultDao {

    @Query("SELECT * FROM unsplash_image_table")
    fun getAllImages(): PagingSource<Int, ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<ResultEntity>)

    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteAllImages()
}
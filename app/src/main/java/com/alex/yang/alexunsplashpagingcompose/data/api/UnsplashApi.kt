package com.alex.yang.alexunsplashpagingcompose.data.api

import com.alex.yang.alexunsplashpagingcompose.BuildConfig
import com.alex.yang.alexunsplashpagingcompose.data.datasource.remote.ResultDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ResultDto>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): List<ResultDto>

}
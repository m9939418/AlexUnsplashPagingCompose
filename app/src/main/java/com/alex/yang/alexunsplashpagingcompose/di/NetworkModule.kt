package com.alex.yang.alexunsplashpagingcompose.di

import com.alex.yang.alexunsplashpagingcompose.data.datasource.remote.UnsplashApi
import com.alex.yang.alexunsplashpagingcompose.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by AlexYang on 2024/2/29.
 *
 *
 */
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeout = 15, TimeUnit.SECONDS)
            .connectTimeout(timeout = 15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType = contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesUnsplashApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }
}
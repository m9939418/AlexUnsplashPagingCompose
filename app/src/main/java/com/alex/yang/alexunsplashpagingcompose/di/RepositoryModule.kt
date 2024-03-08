package com.alex.yang.alexunsplashpagingcompose.di

import com.alex.yang.alexunsplashpagingcompose.data.repository.UnsplashRepositoryImpl
import com.alex.yang.alexunsplashpagingcompose.domain.repository.IUnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by AlexYang on 2024/3/8.
 *
 *
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class UnsplashRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUnsplashRepositoryBind(impl: UnsplashRepositoryImpl): IUnsplashRepository
}
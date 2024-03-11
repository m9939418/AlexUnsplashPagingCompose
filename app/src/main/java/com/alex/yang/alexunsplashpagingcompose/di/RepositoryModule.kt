package com.alex.yang.alexunsplashpagingcompose.di

import com.alex.yang.alexunsplashpagingcompose.data.repository.UnsplashRepositoryImpl
import com.alex.yang.alexunsplashpagingcompose.domain.repository.IUnsplashRepository
import com.alex.yang.alexunsplashpagingcompose.search.data.respository.SearchRepositoryImpl
import com.alex.yang.alexunsplashpagingcompose.search.domain.respository.ISearchRepository
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


@Module
@InstallIn(SingletonComponent::class)
abstract class SearchRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSearchRepositoryBind(impl: SearchRepositoryImpl): ISearchRepository
}
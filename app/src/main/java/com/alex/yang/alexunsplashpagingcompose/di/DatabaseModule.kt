package com.alex.yang.alexunsplashpagingcompose.di

import android.content.Context
import androidx.room.Room
import com.alex.yang.alexunsplashpagingcompose.data.datasource.local.ResultDatabase
import com.alex.yang.alexunsplashpagingcompose.util.Constants.UNSPLASH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by AlexYang on 2024/3/1.
 *
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): ResultDatabase {
        return Room.databaseBuilder(
            context,
            ResultDatabase::class.java,
            UNSPLASH_DATABASE
        ).build()
    }

}
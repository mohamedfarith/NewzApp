package com.app.newzapp.presentation.di.newsFeed

import com.app.network.data.repositoryImpl.NewsRepositoryImpl
import com.app.network.data.services.NewsService
import com.app.network.domain.useCase.NewsFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsFeedModule {
    // give repository instance to news activity
    @Provides
    @Singleton
    fun getNewsService() = NewsService.create()

    @Provides
    @Singleton
    fun getNewsFeedRepo() = NewsRepositoryImpl(getNewsService())

    @Provides
    @Singleton
    fun getNewsFeedUseCase() = NewsFeedUseCase(getNewsFeedRepo())


}
package com.app.newzapp.presentation.di.newsFeed

import com.app.network.data.datasourcesImpl.NewsNetworkDataSourceImpl
import com.app.network.data.repositoryImpl.NewsRepositoryImpl
import com.app.network.data.services.NewsService
import com.app.network.data.usecasimpl.NewsFeedUseCaseImpl
import com.app.network.domain.datasources.NewsNetworkDataSource
import com.app.network.domain.repositories.NewsRepository
import com.app.network.domain.useCase.NewsFeedUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsFeedModule {
    // give repository instance to news activity

    @Binds
    abstract fun getNewsNetworkDataSource(newsNetworkDataSourceImpl: NewsNetworkDataSourceImpl):NewsNetworkDataSource

    @Binds
    abstract fun getNewsFeedRepo(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun getNewsFeedUseCase(nesFeedImpl: NewsFeedUseCaseImpl): NewsFeedUseCase


}
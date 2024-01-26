package com.app.network.data.repositoryImpl

import com.app.network.NetworkState
import com.app.network.data.datasourcesImpl.NewsNetworkDataSourceImpl
import com.app.network.data.services.NewsService
import com.app.network.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val service: NewsService) : NewsRepository {
    override suspend fun fetchNews(
        country: String,
        pageSize: Int,
        pageNumber: Int
    ): NetworkState {
        return NewsNetworkDataSourceImpl(service).getTestingData(country, pageSize, pageNumber)
    }
}
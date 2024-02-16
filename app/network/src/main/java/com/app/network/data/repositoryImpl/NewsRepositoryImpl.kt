package com.app.network.data.repositoryImpl

import com.app.network.NetworkState
import com.app.network.data.datasourcesImpl.NewsNetworkDataSourceImpl
import com.app.network.data.models.NewsArticle
import com.app.network.data.services.NewsService
import com.app.network.domain.datasources.NewsNetworkDataSource
import com.app.network.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val dataSource: NewsNetworkDataSource) :
    NewsRepository {
    override suspend fun fetchNews(
        country: String,
        pageSize: Int,
        pageNumber: Int,
        category: String
    ): Flow<NetworkState<NewsArticle>> {
        return dataSource.getNewsData(country, pageSize, pageNumber, category)
    }
}
package com.app.network.domain.datasources

import com.app.network.NetworkState
import com.app.network.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsNetworkDataSource {
    suspend fun getNewsData(country: String, pageSize: Int, pageNumber: Int,category: String): Flow<NetworkState<NewsArticle>>
    suspend fun getTestingData(country: String, pageSize: Int, pageNumber: Int,category: String): Flow<NetworkState<NewsArticle>>
}
package com.app.network.domain.repositories

import androidx.paging.PagingData
import com.app.network.NetworkState
import com.app.network.data.models.Article
import com.app.network.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun fetchNews(country: String, pageSize: Int, pageNumber: Int,category: String): Flow<NetworkState<NewsArticle>>
}
package com.app.network.domain.datasources

import com.app.network.data.models.NewsArticle

interface NewsNetworkDataSource {
    suspend fun getNewsData(): NewsArticle?
}
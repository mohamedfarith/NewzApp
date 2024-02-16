package com.app.network.domain.useCase

import com.app.network.NetworkState
import com.app.network.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsFeedUseCase {

    suspend fun invoke(
        country: String,
        pageSize: Int,
        pageNumber: Int,
        category: String
    ): Flow<NetworkState<NewsArticle>>
}

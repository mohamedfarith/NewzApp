package com.app.network.data.usecasimpl

import com.app.network.NetworkState
import com.app.network.data.models.NewsArticle
import com.app.network.domain.repositories.NewsRepository
import com.app.network.domain.useCase.NewsFeedUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFeedUseCaseImpl @Inject constructor(private val repository: NewsRepository) :
    NewsFeedUseCase {
    override suspend fun invoke(
        country: String,
        pageSize: Int,
        pageNumber: Int,
        category: String
    ): Flow<NetworkState<NewsArticle>> {
        return repository.fetchNews(country, pageSize, pageNumber, category)
    }


}
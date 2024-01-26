package com.app.network.domain.useCase

import androidx.paging.PagingData
import com.app.network.NetworkState
import com.app.network.data.models.Article
import com.app.network.data.models.NewsArticle
import com.app.network.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFeedUseCase @Inject constructor( val repository: NewsRepository) {

     suspend fun invoke(country: String, pageSize: Int, pageNumber: Int): NetworkState {
       return repository.fetchNews(country, pageSize, pageNumber)
    }
}

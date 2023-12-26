package com.app.network.domain.useCase

import androidx.paging.PagingData
import com.app.network.data.models.Article
import com.app.network.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFeedUseCase @Inject constructor( val repository: NewsRepository) {

     fun invoke(country: String, pageSize: Int, pageNumber: Int): Flow<PagingData<Article>> {
       return repository.fetchNews(country, pageSize, pageNumber)
    }
}

package com.app.newzapp.presentation.ui.newsFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.network.data.models.Article
import com.app.network.domain.useCase.NewsFeedUseCase
import kotlinx.coroutines.flow.Flow

class NewsViewModel() : ViewModel() {
    fun getLatestNewsData(
        useCase: NewsFeedUseCase,
        country: String,
        pageSize: Int,
        pageNumber: Int
    ): Flow<androidx.paging.PagingData<Article>> =
        useCase.invoke(country = country, pageSize = pageSize, pageNumber = pageNumber)
            .cachedIn(viewModelScope)
}
package com.app.newzapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.network.news.NewsRepository
import com.app.network.news.models.Article
import com.app.network.news.models.NewsArticle
import com.app.newzapp.UiState
import kotlinx.coroutines.flow.Flow

class NewsViewModel() : ViewModel() {

    private val _newsData = MutableLiveData<UiState<NewsArticle?>?>()
    val newsData: LiveData<UiState<NewsArticle?>?> get() = _newsData

    fun getLatestNewsData(
        repository: NewsRepository,
        country: String,
        pageSize: Int,
        pageNumber: Int
    ): Flow<androidx.paging.PagingData<Article>> = repository.getNewsList(country = country, pageSize = pageSize, pageNumber = pageNumber).cachedIn(viewModelScope)


}
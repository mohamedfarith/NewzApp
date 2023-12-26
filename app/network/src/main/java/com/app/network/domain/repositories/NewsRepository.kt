package com.app.network.domain.repositories

import androidx.paging.PagingData
import com.app.network.data.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchNews(country: String, pageSize: Int, pageNumber: Int): Flow<PagingData<Article>>
}
package com.app.network.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.network.data.datasourcesImpl.NewsNetworkDataSourceImpl
import com.app.network.data.models.Article
import com.app.network.data.services.NewsService
import com.app.network.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (  val service: NewsService) : NewsRepository {
    override fun fetchNews(
        country: String,
        pageSize: Int,
        pageNumber: Int
    ): Flow<PagingData<Article>> {
        return Pager(pagingSourceFactory = {
            NewsNetworkDataSourceImpl(service).apply {
                setApiCallData(country = country, pageSize = pageSize, pageNumber = pageNumber)
            }
        }, config = PagingConfig(pageSize = pageSize)).flow
    }
}
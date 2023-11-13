package com.app.network.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.network.news.models.Article
import kotlinx.coroutines.flow.Flow

/*
* This class decides whether to get data from local or from server
* */
class NewsRepository internal constructor(private val service: NewsService) {

     fun getNewsList(country: String, pageSize: Int, pageNumber: Int): Flow<PagingData<Article>> {
        return Pager(pagingSourceFactory = {
            NewsNetworkDataSource(service).apply {
                setApiCallData(country = country, pageSize = pageSize, pageNumber = pageNumber)
            }
        }, config = PagingConfig(pageSize = pageSize)).flow
    }
}
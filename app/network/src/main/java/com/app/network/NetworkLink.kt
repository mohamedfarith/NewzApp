package com.app.network

import com.app.network.news.NewsRepository
import com.app.network.news.NewsService

object NetworkLink {
    val newsRepository = NewsRepository(NewsService.create())
}
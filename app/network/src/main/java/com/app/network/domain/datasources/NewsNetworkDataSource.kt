package com.app.network.domain.datasources

import com.app.network.NetworkState

interface NewsNetworkDataSource {
    suspend fun getNewsData(country: String, pageSize: Int, pageNumber: Int): NetworkState
}
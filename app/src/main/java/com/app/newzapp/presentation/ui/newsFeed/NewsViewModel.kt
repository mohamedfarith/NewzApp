package com.app.newzapp.presentation.ui.newsFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.NetworkState
import com.app.network.data.models.NewsArticle
import com.app.network.domain.useCase.NewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: NewsFeedUseCase) : ViewModel() {

    private val _newsState = MutableStateFlow<NetworkState<NewsArticle>>(NetworkState.Loading)
    val newState: StateFlow<NetworkState<NewsArticle>> get() = _newsState

    fun getLatestNewsData(
        country: String,
        pageSize: Int,
        pageNumber: Int,
        category: String

    ) {
        if (validFetchNewsInput(country = country, pageSize = pageSize, pageNumber = pageNumber)) {
            _newsState.value = NetworkState.Loading
            viewModelScope.launch(Dispatchers.IO) {
                useCase.invoke(country, pageSize, pageNumber, category).collect {
                    _newsState.value = it
                }
            }
        }
    }

    private fun validFetchNewsInput(
        country: String?,
        pageSize: Int?,
        pageNumber: Int?,
    ): Boolean {
        return country.isNullOrBlank().not() && (pageSize != null) && (pageNumber != null)
    }
}
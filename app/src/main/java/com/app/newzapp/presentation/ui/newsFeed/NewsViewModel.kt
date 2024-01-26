package com.app.newzapp.presentation.ui.newsFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.NetworkState
import com.app.network.domain.useCase.NewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val useCase: NewsFeedUseCase) : ViewModel() {

    private val _newsState = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val newState: StateFlow<NetworkState> get() = _newsState

    fun getLatestNewsData(
        country: String,
        pageSize: Int,
        pageNumber: Int
    ) {
        if (validFetchNewsInput(country = country, pageSize = pageSize, pageNumber = pageNumber)) {
            viewModelScope.launch(Dispatchers.IO) {
                flow<NetworkState> {
                    emit(useCase.invoke(country = country, pageNumber = pageNumber, pageSize = pageSize))
                }.collect() { state: NetworkState ->
                    _newsState.value = state
                }
            }
        }
    }

    private fun validFetchNewsInput(country: String?, pageSize: Int?, pageNumber: Int?): Boolean {
        return country.isNullOrBlank().not() && (pageSize != null) && (pageNumber != null)
    }
}
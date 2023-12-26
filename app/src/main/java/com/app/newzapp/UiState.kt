package com.app.newzapp

sealed class UiState<out T> {
    class Success<T>(val data: T?) : UiState<T>()
    class Failure<T>(val message: String) : UiState<T>()
    object Loading : UiState<Nothing>()
}
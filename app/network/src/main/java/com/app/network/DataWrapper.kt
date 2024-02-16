package com.app.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


object DataWrapper {
    inline  fun <reified T> invoke(response: Response<T>): Flow<NetworkState<T>> {
        return flow {
            if (response.isSuccessful) {
               emit(NetworkState.Success(data = response.body() as T))
            } else {
               emit(NetworkState.Failure(message = response.message().toString()))
            }
        }
    }

}
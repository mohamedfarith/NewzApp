package com.app.network.data.services

import android.content.Context
import com.app.network.NetworkConstants
import com.app.network.NetworkConstants.BASE_URL
import com.app.network.data.models.NewsArticle
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

// This will contain all the apis referencing to the fetch of all news
interface NewsService {
    @GET("top-headlines")
    suspend fun getNewsData(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") pageNumber: Int,
        @Query("category") category: String
    ): retrofit2.Response<NewsArticle>

    companion object {
        fun create(context: Context): NewsService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor(HttpInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }
    }
}

class HttpInterceptor : Interceptor {
    //intercepting and adding headers, here it is API KEY
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder().maxAge(1, TimeUnit.HOURS).build()
        val request = chain.request()
            .newBuilder().addHeader(NetworkConstants.API_NAME, NetworkConstants.API_KEY).build()
        return chain.proceed(request)
    }

}
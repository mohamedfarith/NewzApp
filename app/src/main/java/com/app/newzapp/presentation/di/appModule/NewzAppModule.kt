package com.app.newzapp.presentation.di.appModule

import android.content.Context
import android.content.SharedPreferences
import com.app.network.data.models.TopNavigationItem
import com.app.network.data.services.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewzAppModule {

    // shared preferences instance

    // retrofit instance for the given service
    @Provides
    @Singleton
    fun getNewsService(@ApplicationContext context: Context) = NewsService.create(context)

    @Singleton
    @Provides
    fun sharedPreferenceInstance(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("ABC", Context.MODE_PRIVATE)
    }

    fun generateTopNavItems(): List<TopNavigationItem> {
        val topNavigators = listOf(
            "Business",
            "Entertainment",
            "General",
            "Health",
            "Science",
            "Sports",
            "Technology"
        )
        return topNavigators.map { TopNavigationItem(route = "/$it", title = it) }
    }
}
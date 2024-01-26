package com.app.newzapp.presentation.di.appModule

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewzAppModule {

    // shared preferences instance

    // retrofit instance for the given service

    @Singleton
    @Provides
    fun sharedPreferenceInstance(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("ABC", Context.MODE_PRIVATE)
    }
}
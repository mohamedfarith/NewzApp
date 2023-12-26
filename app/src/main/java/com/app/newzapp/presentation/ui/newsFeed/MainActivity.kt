package com.app.newzapp.presentation.ui.newsFeed

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.network.domain.useCase.NewsFeedUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var newsFeedUseCase: NewsFeedUseCase

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NewsFeedScreen(viewModel, newsFeedUseCase, sharedPreferences){}
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "news_feed") {
//
//                composable("news_feed") {
//
//                    NewsFeedScreen(viewModel) {
//
//                    }
//
//                }
//                composable("news_description") {
//
//                }
//
//            }
        }
    }
}
package com.app.newzapp.news.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.network.NetworkConstants
import com.app.network.NetworkLink
import com.app.newzapp.news.NewsViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsFeedScreen(viewModel) {}
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
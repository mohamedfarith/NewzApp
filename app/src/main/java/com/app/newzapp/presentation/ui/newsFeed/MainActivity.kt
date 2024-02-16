package com.app.newzapp.presentation.ui.newsFeed

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.WHITE
        setContent {
            val navController = rememberNavController()
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                scrollBehavior = scrollBehavior
            )


        }
    }
}


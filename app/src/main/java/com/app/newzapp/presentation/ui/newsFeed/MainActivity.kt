package com.app.newzapp.presentation.ui.newsFeed

import android.content.SharedPreferences
import android.graphics.Color
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.WHITE
        setContent {
            NewsFeedScreen(viewModel) {
                // on article click handle the event and move to next screen
            }
        }
    }
}
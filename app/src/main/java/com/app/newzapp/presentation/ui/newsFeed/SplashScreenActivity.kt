package com.app.newzapp.presentation.ui.newsFeed

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            statusBarColor = Color.WHITE
        }
        setContent {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = androidx.compose.ui.graphics.Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "NewzApp", fontFamily = FontFamily.Cursive, fontSize = 52.sp)
            }
        }
        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
    }
}
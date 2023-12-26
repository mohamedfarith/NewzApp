package com.app.newzapp.presentation.ui.newsFeed

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.network.NetworkConstants
import com.app.network.data.models.Article
import com.app.network.domain.useCase.NewsFeedUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    viewModel: NewsViewModel,
    newsFeedUseCase: NewsFeedUseCase,
    sharedPreferences: SharedPreferences,
    onCardClick: (Article) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val loadedItem = viewModel.getLatestNewsData(
        useCase = newsFeedUseCase,
        country = "in", pageSize = NetworkConstants.DEFAULT_PAGE_LIMIT, pageNumber = 1
    ).collectAsLazyPagingItems()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Discover",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )

                },
                navigationIcon = {},
                actions = {},
                scrollBehavior = scrollBehavior
            )
        },
        content = { padding ->

            LazyColumn(modifier = Modifier.padding(padding)) {
                items(count = loadedItem.itemCount) { index ->
                    val item = loadedItem[index]
                    item?.let { it ->
                        ArticleCard(article = it, onCardClick = {

                        })
                    }

                }
                when (loadedItem.loadState.refresh) {
                    is LoadState.Loading -> {

                    }

                    is LoadState.Error -> {

                    }

                    else -> {}
                }
                when (loadedItem.loadState.append) {
                    is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }

                    is LoadState.Loading -> {

                    }

                    else -> {

                    }
                }
            }


        }
    )
}

@Composable
fun ArticleCard(article: Article, onCardClick: (Article) -> Unit) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clickable { onCardClick(article) },
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.White)
                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = article.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .height(80.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Fit,
                    contentDescription = "some icon"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = article.title ?: "",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Text(
                            text = article.getPublishedDate() ?: "",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = article.author ?: "",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ArticleCardPreview() {
    ArticleCard(
        Article(
            author = "Rajamanickam",
            title = "Canadian PM is not well",
            description = "Hello how are you",
            url = "https://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/asteroid_blend.png",
            urlToImage = "https://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/asteroid_blend.png",
            publishedAt = "2023-11-10T15:25:12Z"
        ), onCardClick = {

        }
    )
}

@Preview
@Composable
fun NewsFeedScreenPreview() {
//    NewsFeedScreen() {}
}
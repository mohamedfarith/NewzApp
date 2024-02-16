package com.app.newzapp.presentation.ui.newsFeed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.app.network.NetworkConstants
import com.app.network.NetworkState
import com.app.network.data.models.Article
import com.app.network.data.models.NewsArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    viewModel: NewsViewModel,
    category: String?,
    onCardClick: (Article) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val loadedItem = remember {
        mutableStateOf<NewsArticle?>(null)
    }
    val mCategory = remember {
        mutableStateOf(category)
    }
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val latestEvent = remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event -> latestEvent.value = event }
        lifecycleOwner.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.removeObserver(lifecycleObserver) }
    }
    LaunchedEffect(key1 = mCategory.value) {
        if (latestEvent.value == Lifecycle.Event.ON_START || latestEvent.value == Lifecycle.Event.ON_RESUME) {
            viewModel.getLatestNewsData(
                country = "in",
                pageSize = NetworkConstants.DEFAULT_PAGE_LIMIT,
                pageNumber = 1,
                category = category?.replace("/", "")?.lowercase().toString()
            )
        }

    }

    when (val item = viewModel.newState.collectAsState().value) {
        is NetworkState.Success -> run {
            loadedItem.value = item.data as? NewsArticle
            loadedItem.value?.let { newsArticle ->
                LazyColumn(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
                    itemsIndexed(
                        newsArticle.articles?.toList() ?: emptyList(),
                        key = { _, item -> item?.title.hashCode() }) { _, article ->
                        article?.let {
                            ArticleCard(article = article, onCardClick = {})
                            Divider(
                                modifier = Modifier.alpha(0.2f),
                                thickness = 1.dp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }

        is NetworkState.Loading -> run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.width(50.dp), color = Color.Black)
            }
        }

        is NetworkState.Failure -> run {
            Toast.makeText(LocalContext.current, "Api Call failed", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun ArticleCard(article: Article, onCardClick: (Article) -> Unit) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 5.dp)
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
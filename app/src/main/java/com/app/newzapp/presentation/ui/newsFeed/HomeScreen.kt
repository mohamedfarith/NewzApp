package com.app.newzapp.presentation.ui.newsFeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.newzapp.presentation.di.appModule.NewzAppModule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CollapsingComponent(navController, scrollBehavior = scrollBehavior) {
        NavHost(navController = navController, startDestination = "/Business") {
            composable("/Business") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/Entertainment") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/General") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/Health") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/Science") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/Sports") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
            composable("/Technology") {
                NewsFeedScreen(
                    viewModel = viewModel,
                    category = it.destination.route,
                    scrollBehavior = scrollBehavior,
                    onCardClick = {

                    })
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingComponent(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable ColumnScope.() -> Unit
) {
    val entry by navController.currentBackStackEntryAsState()
    val selectedTabIndex = remember { mutableStateOf(0) }
    val currentRoute = entry?.destination?.route
    val navItemsList =
       NewzAppModule.generateTopNavItems()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeScreenAppBar(scrollBehavior = scrollBehavior)
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            ScrollableTabRow(
                edgePadding = 0.dp,
                backgroundColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Color.Red,
                        height = 2.dp,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                    )
                },
                selectedTabIndex = selectedTabIndex.value,
            ) {
                navItemsList.forEachIndexed { index, item ->
                    Text(
                        item.title ?: "", modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                if (currentRoute != item.route) {
                                    selectedTabIndex.value = index
                                    navController.navigate(item.route ?: "")
                                }
                            }, textAlign = TextAlign.Center
                    )
                }

            }
            content(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White),
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 20.dp),
                    textAlign = TextAlign.Center,
                    text = "Headlines",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Cursive
                )
                Icon(
                    modifier = Modifier.padding(top = 5.dp, end = 20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            }


        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CollapsingComponentPrev() {
    CollapsingComponent(
        navController = rememberNavController(),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    ) {

    }

}



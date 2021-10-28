package com.nividata.owls.view.newMain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.nividata.owls.R
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.amazon.AmazonView
import com.nividata.owls.view.hotstar.HotstarView
import com.nividata.owls.view.main.Toolbar
import com.nividata.owls.view.netflix.NetflixView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun NewMainView(
    navController: NavHostController
) {
    val tabs = listOf(Screen.Netflix, Screen.Amazon, Screen.Hotstar)
    val pagerState = rememberPagerState(pageCount = tabs.size)

    Scaffold(topBar = {
        Toolbar()
    }) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(
                tabs = tabs,
                pagerState = pagerState,
                navController = navController,
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<Screen>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = R.color.black),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Icon(painter = painterResource(id = tab.icon), contentDescription = "")
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun TabsContent(
    tabs: List<Screen>,
    pagerState: PagerState,
    navController: NavHostController,
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> NetflixView(viewModel = hiltViewModel(), navController = navController)
            1 -> AmazonView(viewModel = hiltViewModel(), navController = navController)
            2 -> HotstarView(viewModel = hiltViewModel(), navController = navController)
        }
    }
}
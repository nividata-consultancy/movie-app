package com.nividata.owls.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.nividata.owls.view.netflix.NetflixView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainView(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val tabs = listOf(Screen.Netflix, Screen.Amazon, Screen.Hotstar)
    val pagerState = rememberPagerState(pageCount = tabs.size)

    val onPageChange: (
        index: Int
    ) -> Unit = { index ->
        viewModel.setEvent(MainContract.Event.ChangeTheme(index))
    }

    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState, onPageChange = onPageChange)
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
fun Tabs(tabs: List<Screen>, pagerState: PagerState, onPageChange: (Int) -> Unit) {
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
                    onPageChange(index)
                },
                selectedContentColor = tab.selectedContentColor!!
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = tab.icon),
                        contentDescription = "",
                        alpha = if (pagerState.currentPage == index) 1f else 0.5f
                    )
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
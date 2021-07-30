package com.nividata.owls.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.R
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.home.HomeView
import com.nividata.owls.view.tv.TvView
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun MainView(
    navMainController: NavController
) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        Toolbar()
    }, bottomBar = {
        BottomAppBar(navController = navController)
    }) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeView(hiltViewModel())
            }
            composable(Screen.Tv.route) {
                TvView()
            }
        }
    }
}

@Composable
fun Toolbar() {
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
}

@Composable
fun BottomAppBar(
    navController: NavController
) {
    BottomNavigation(
        backgroundColor = Color.Black,
        elevation = 4.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        listOf(
            Screen.Home,
            Screen.Tv
        ).forEach { list ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = list.icon),
                        contentDescription = list.name,
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == list.route } == true,
                onClick = {
                    navController.navigate(list.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.2f)
            )
        }
    }
}
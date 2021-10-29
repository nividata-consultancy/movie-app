package com.nividata.owls.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.utils.assistedViewModel
import com.nividata.owls.view.main.MainView
import com.nividata.owls.view.movieDetails.MovieDetailsView
import com.nividata.owls.view.movieDetails.MovieDetailsViewModel
import com.nividata.owls.view.newMain.NewMainView
import com.nividata.owls.view.splash.SplashView
import com.nividata.owls.view.tvDetails.TvDetailsView
import com.nividata.owls.view.tvDetails.TvDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

const val NOTY_NAV_HOST_ROUTE = "noty-main-route"

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun OwlsNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Splash.route, route = NOTY_NAV_HOST_ROUTE) {
        composable(Screen.Splash.route) {
            SplashView(navController, hiltViewModel())
        }
        composable(Screen.Main.route) {
            MainView(navController)
        }
        composable(Screen.NewMain.route) {
            NewMainView(navController)
        }
        composable(
            Screen.MovieDetail.route,
            arguments = listOf(
                navArgument(Screen.MovieDetail.ARG_MOVIE_ID) { type = NavType.IntType }
            )
        ) {
            val movieId = it.arguments?.getInt(Screen.MovieDetail.ARG_MOVIE_ID)
                ?: throw IllegalStateException("'movieId' shouldn't be null")
            MovieDetailsView(
                navController, assistedViewModel {
                    MovieDetailsViewModel.provideFactory(movieDetailViewModelFactory(), movieId)
                }
            )
        }
        composable(
            Screen.TvDetail.route,
            arguments = listOf(
                navArgument(Screen.TvDetail.ARG_TV_ID) { type = NavType.IntType }
            )
        ) {
            val tvId = it.arguments?.getInt(Screen.TvDetail.ARG_TV_ID)
                ?: throw IllegalStateException("'tvId' shouldn't be null")
            TvDetailsView(
                navController, assistedViewModel {
                    TvDetailsViewModel.provideFactory(tvDetailViewModelFactory(), tvId)
                }
            )
        }
    }
}
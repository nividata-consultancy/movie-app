package com.nividata.owls.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.view.MainActivity
import com.nividata.owls.view.main.MainView
import com.nividata.owls.view.movieDetails.MovieDetailsView
import com.nividata.owls.view.movieDetails.MovieDetailsViewModel
import com.nividata.owls.view.newMain.NewMainView
import com.nividata.owls.view.splash.SplashView
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

const val NOTY_NAV_HOST_ROUTE = "noty-main-route"

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
            MovieDetailsView(navController,  hiltViewModel()/*assistedViewModel {
                MovieDetailsViewModel.provideFactory(noteDetailViewModelFactory(), movieId)
            }*/)
        }
        /*
        composable(Screen.AddNote.route) {
            AddNoteScreen(navController, hiltViewModel())
        }
        composable(Screen.Notes.route) {
            NotesScreen(toggleTheme, navController, hiltViewModel())
        }

        composable(Screen.About.route) {
            AboutScreen(navController = navController)
        }
        */
    }
}
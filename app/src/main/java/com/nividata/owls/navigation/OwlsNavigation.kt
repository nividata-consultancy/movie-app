package com.nividata.owls.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.view.main.MainView
import com.nividata.owls.view.newMain.NewMainView
import com.nividata.owls.view.splash.SplashView
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val NOTY_NAV_HOST_ROUTE = "noty-main-route"

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

        /*
        composable(Screen.AddNote.route) {
            AddNoteScreen(navController, hiltViewModel())
        }
        composable(Screen.Notes.route) {
            NotesScreen(toggleTheme, navController, hiltViewModel())
        }
        composable(
            Screen.NotesDetail.route,
            arguments = listOf(
                navArgument(Screen.NotesDetail.ARG_NOTE_ID) { type = NavType.StringType }
            )
        ) {
            val noteId = it.arguments?.getString(Screen.NotesDetail.ARG_NOTE_ID)
                ?: throw IllegalStateException("'noteId' shouldn't be null")
            NoteDetailsScreen(navController, noteDetailViewModel(noteId))
        }
        composable(Screen.About.route) {
            AboutScreen(navController = navController)
        }
        */
    }
}
package com.nividata.owls.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.view.common.MovieListView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val upcomingMovies = viewModel.upcomingMovies.collectAsState(initial = null).value
    val availableMovies = viewModel.availableMovies.collectAsState(initial = null).value

//    val onNoteClicked: (Note) -> Unit = {
//        println("Note Clicked")
//        navController.navigate(Screen.NotesDetail.route(it.id))
//    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (upcomingMovies) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> UpcomingMovieList(upcomingMovies.data.results)
            is ViewState.Failed -> Text(text = upcomingMovies.message)
        }
        when (availableMovies) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> MovieListView(
                movieList = availableMovies.data.results,
                title = "Available now"
            )
            is ViewState.Failed -> Text(text = availableMovies.message)
        }
    }
}
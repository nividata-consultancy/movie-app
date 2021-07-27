package com.nividata.owls.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val trendingMovies = viewModel.trendingMovies.collectAsState(initial = null).value
    val popularMoviesOnNetflix =
        viewModel.popularMoviesOnNetflix.collectAsState(initial = null).value
    val popularMoviesOnAmazon = viewModel.popularMoviesOnAmazon.collectAsState(initial = null).value
    val popularMoviesOnHotstar =
        viewModel.popularMoviesOnHotstar.collectAsState(initial = null).value

//    val onNoteClicked: (Note) -> Unit = {
//        println("Note Clicked")
//        navController.navigate(Screen.NotesDetail.route(it.id))
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
        when (trendingMovies) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> MovieListView(
                movieList = trendingMovies.data.results,
                title = "Trending"
            )
            is ViewState.Failed -> Text(text = trendingMovies.message)
        }
        when (popularMoviesOnNetflix) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> MovieListView(
                movieList = popularMoviesOnNetflix.data.results,
                title = "Netflix"
            )
            is ViewState.Failed -> Text(text = popularMoviesOnNetflix.message)
        }
        when (popularMoviesOnAmazon) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> MovieListView(
                movieList = popularMoviesOnAmazon.data.results,
                title = "Amazon"
            )
            is ViewState.Failed -> Text(text = popularMoviesOnAmazon.message)
        }
        when (popularMoviesOnHotstar) {
            is ViewState.Loading, null -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is ViewState.Success -> MovieListView(
                movieList = popularMoviesOnHotstar.data.results,
                title = "Disney+ Hotstar"
            )
            is ViewState.Failed -> Text(text = popularMoviesOnHotstar.message)
        }
    }
}
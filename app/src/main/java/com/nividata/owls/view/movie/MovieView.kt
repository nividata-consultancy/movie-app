package com.nividata.owls.view.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.view.common.ListView
import com.nividata.owls.view.common.SliderView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun HomeView(
    viewModel: MovieViewModel
) {
    val state = viewModel.viewState.value

//    val onNoteClicked: (Note) -> Unit = {
//        println("Note Clicked")
//        navController.navigate(Screen.NotesDetail.route(it.id))
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        when (state) {
            is MovieContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is MovieContract.State.Success -> {
                state.homeMovieList.forEachIndexed { index, homeMovieList ->
                    if (index == 0) {
                        SliderView(homeMovieList.movieList)
                    } else {
                        ListView(
                            movieList = homeMovieList.movieList,
                            title = homeMovieList.title
                        )
                    }
                }
            }
            is MovieContract.State.Failed -> Text(text = state.message)
        }
    }
}
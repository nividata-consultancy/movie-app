package com.nividata.owls.view.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.domain.model.Movie
import com.nividata.owls.view.common.ListView
import com.nividata.owls.view.common.SmallHCardView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun HomeView(
    viewModel: MovieViewModel,
) {
    val state = viewModel.viewState.value
    val onItemClicked: (id: Int, type: String) -> Unit = { id, type -> }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        when (state) {
            is MovieContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is MovieContract.State.Success -> {
                state.homeMovieList.forEachIndexed { index, homeMovieList ->
                    when (index) {
//                        0 -> {
//                            SliderView(
//                                homeMovieList.movieList,
//                                title = homeMovieList.title,
//                                onMovieClicked = onMovieClicked,
//                            )
//                        }
                        2 -> {
                            UpcomingMovie(movieList = homeMovieList.movieList)
                        }
                        else -> {
                            ListView(
                                movieList = homeMovieList.movieList,
                                title = homeMovieList.title,
                                onItemClicked = onItemClicked
                            )
                        }
                    }
                }
            }
            is MovieContract.State.Failed -> Text(text = state.message)
        }
    }
}

@Composable
fun UpcomingMovie(movieList: List<Movie>) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .background(
                Color.Black.copy(blue = 0.11f, red = 0.1f, green = 0.05f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(bottom = 10.dp),
    ) {
        Text(
            text = "Upcoming",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(movieList) {
                SmallHCardView(movie = it)
            }
        }
    }
}
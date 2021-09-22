package com.nividata.owls.view.netflix

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.nividata.owls.view.common.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NetflixView(
    viewModel: NetflixViewModel
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
            is NetflixContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is NetflixContract.State.Success -> {
                state.homeMovieList.forEachIndexed { index, homeMovieList ->
                    when (index) {
                        0 -> {
                            SliderView(homeMovieList.movieList, title = homeMovieList.title)
                        }
                        1 -> {
                            Top10Movie(homeMovieList.movieList, title = homeMovieList.title)
                        }
                        else -> {
                            ListView(
                                movieList = homeMovieList.movieList,
                                title = homeMovieList.title
                            )
                        }
                    }
                }
            }
            is NetflixContract.State.Failed -> Text(text = state.message)
        }
    }
}

@Composable
fun Top10Movie(movieList: List<Movie>, title: String) {
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(movieList) { index, item ->
                RoundCardView(movie = item, index)
            }
        }
    }
}

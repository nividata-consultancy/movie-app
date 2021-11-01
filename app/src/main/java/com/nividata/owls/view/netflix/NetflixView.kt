package com.nividata.owls.view.netflix

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.domain.model.Movie
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.nividata.owls.view.common.ListView
import com.nividata.owls.view.common.RoundCardView
import com.nividata.owls.view.common.SliderView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NetflixView(
    navController: NavHostController,
    viewModel: NetflixViewModel,
) {
    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, type ->
        viewModel.setEvent(NetflixContract.Event.NetflixItemSelection(id, type))
    }

    val onMoreIconClicked: (
        categoryType: String,
        categoryName: String
    ) -> Unit = { categoryName, categoryType ->
        viewModel.setEvent(
            NetflixContract.Event.MovieListSelection(
                categoryName = categoryName,
                categoryType = categoryType
            )
        )
    }

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is NetflixContract.Effect.Navigation.ToMovieDetails -> {
                    navController.navigate(Screen.MovieDetail.route(effect.movieId))
                }
                is NetflixContract.Effect.Navigation.ToTvDetails -> {
                    navController.navigate(Screen.TvDetail.route(effect.tvId))
                }
                is NetflixContract.Effect.Navigation.ToMovieList -> {
                    navController.navigate(
                        Screen.MovieList.route(
                            categoryName = effect.categoryName,
                            categoryType = effect.categoryType
                        )
                    )
                }
            }
        }.collect()
    }

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
                            SliderView(
                                homeMovieList.movieList,
                                title = homeMovieList.title,
                                onItemClicked = onItemClicked
                            )
                        }
                        1 -> {
                            Top10Movie(
                                homeMovieList.movieList,
                                title = homeMovieList.title,
                                onItemClicked = onItemClicked
                            )
                        }
                        else -> {
                            ListView(
                                movieList = homeMovieList.movieList,
                                title = homeMovieList.title,
                                onItemClicked = onItemClicked,
                                onMoreIconClicked = onMoreIconClicked,
                                categoryType = homeMovieList.categoryType!!,
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
fun Top10Movie(movieList: List<Movie>, title: String, onItemClicked: (Int, String) -> Unit) {
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
                RoundCardView(movie = item, index, onItemClicked = onItemClicked)
            }
        }
    }
}

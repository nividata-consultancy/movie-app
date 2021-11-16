package com.nividata.movie_time.view.hotstar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.movie_time.navigation.Screen
import com.nividata.movie_time.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.nividata.movie_time.view.common.ErrorMessageView
import com.nividata.movie_time.view.common.ListView
import com.nividata.movie_time.view.common.ProgressView
import com.nividata.movie_time.view.common.SliderView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun HotstarView(
    navController: NavHostController,
    viewModel: HotstarViewModel,
) {
    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, type ->
        viewModel.setEvent(HotstarContract.Event.HotstarItemSelection(id, type))
    }

    val onMoreIconClicked: (
        categoryType: String,
        categoryName: String
    ) -> Unit = { categoryType, categoryName ->
        viewModel.setEvent(
            HotstarContract.Event.MovieListSelection(
                categoryName = categoryName,
                categoryType = categoryType
            )
        )
    }

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is HotstarContract.Effect.Navigation.ToMovieDetails -> {
                    navController.navigate(Screen.MovieDetail.route(effect.movieId))
                }
                is HotstarContract.Effect.Navigation.ToTvDetails -> {
                    navController.navigate(Screen.TvDetail.route(effect.tvId))
                }
                is HotstarContract.Effect.Navigation.ToMovieList -> {
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

    when (state) {
        is HotstarContract.State.Loading -> ProgressView()
        is HotstarContract.State.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                state.homeMovieList.forEachIndexed { index, homeMovieList ->
                    when (index) {
                        0 -> {
                            SliderView(
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
        }
        is HotstarContract.State.Failed -> ErrorMessageView(message = state.message)
    }
}

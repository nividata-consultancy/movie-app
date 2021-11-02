package com.nividata.owls.view.movieList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.view.common.CardView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun MovieListView(
    categoryName: String,
    navController: NavHostController,
    viewModel: MovieListViewModel
) {
//    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, type ->
//        viewModel.setEvent(MovieListContract.Event.MovieSelection(id, type))
    }

//    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
//        viewModel.effect.onEach { effect ->
//            when (effect) {
//                is MovieListContract.Effect.Navigation.ToMovieDetails -> {
//                    navController.navigate(Screen.MovieDetail.route(effect.movieId))
//                }
//                is MovieListContract.Effect.Navigation.ToTvDetails -> {
//                    navController.navigate(Screen.TvDetail.route(effect.tvId))
//                }
//            }
//        }.collect()
//    }

    val lazyMovieItems = viewModel.movies.collectAsLazyPagingItems()
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = categoryName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.padding(12.dp)
        ) {
            items(lazyMovieItems.itemCount) { index ->
                lazyMovieItems[index]?.let {
                    Box(modifier = Modifier.padding(8.dp)) {
                        CardView(movie = it, onItemClicked = onItemClicked)
                    }
                }
            }
        }
    }
//    when (state) {
//        is MovieListContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
//        is MovieListContract.State.Success -> {
//        }
//        is MovieListContract.State.Failed -> Text(text = state.message)
//    }
}



package com.nividata.owls.view.tv

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

@ExperimentalPagerApi
@Composable
fun TvView(
    viewModel: TvViewModel
) {
    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, type -> }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        when (state) {
            is TvContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is TvContract.State.Success -> {
                state.homeTvList.forEachIndexed { index, homeMovieList ->
//                    if (index == 0) {
//                        SliderView(homeMovieList.tvList, title = homeMovieList.title)
//                    } else {
                    ListView(
                        movieList = homeMovieList.tvList,
                        title = homeMovieList.title,
                        onItemClicked = onItemClicked
                    )
//                    }
                }
            }
            is TvContract.State.Failed -> Text(text = state.message)
        }
    }
}
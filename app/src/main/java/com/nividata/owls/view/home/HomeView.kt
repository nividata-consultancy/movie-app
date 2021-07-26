package com.nividata.owls.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.model.response.MovieResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val notesState = viewModel.notes.collectAsState(initial = null).value

//    val onNoteClicked: (Note) -> Unit = {
//        println("Note Clicked")
//        navController.navigate(Screen.NotesDetail.route(it.id))
//    }

    when (notesState) {
        is ViewState.Loading, null -> CircularProgressIndicator()
        is ViewState.Success -> HomeContent(notesState.data.results)
        is ViewState.Failed -> Text(text = notesState.message)
    }
}

@ExperimentalPagerApi
@Composable
fun HomeContent(movieList: List<MovieResponse>) {
    UpcomingMovieList(movieList = movieList)
}

@ExperimentalPagerApi
@Composable
fun UpcomingMovieList(movieList: List<MovieResponse>) {
    HorizontalPager(
        state = rememberPagerState(
            pageCount = movieList.size,
            infiniteLoop = true,
            initialOffscreenLimit = 3,
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(it).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.84f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.4f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth(0.75f)
                .height(170.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
        ) {
            Box() {
                Image(
                    painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(movieList[it].backdrop_path)),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = movieList[it].title.uppercase(Locale.getDefault()),
                    fontSize = 16.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.White,
                            blurRadius = 6f,
                            offset = Offset(0f, 2f)
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
            }
        }
    }
}
package com.nividata.owls.view.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.model.CastCrew
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.model.MovieDetails
import com.nividata.owls.view.common.CastCrewView
import com.nividata.owls.view.common.ListView
import com.nividata.owls.view.common.RatingBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun MovieDetailsView(
    navController: NavHostController,
    viewModel: MovieDetailsViewModel
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
            is MovieDetailsContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is MovieDetailsContract.State.Success -> {
                DetailsView(
                    movieDetails = state.movieDetails,
                    castCrew = state.castCrew,
                    recommendations = state.recommendations
                )
            }
            is MovieDetailsContract.State.Failed -> Text(text = state.message)
        }
    }
}

@Composable
fun DetailsView(
    movieDetails: MovieDetails,
    castCrew: CastCrew,
    recommendations: HomeMovieList
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(movieDetails.backdrop_path)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .height(300.dp)
//                .background(Color.Red)
                .clip(object : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {
                        val height = 100f
                        return Outline.Generic(Path().apply {
                            reset()
                            lineTo(x = 0f, y = size.height - height)
                            cubicTo(
                                x1 = 0f,
                                y1 = size.height - height,
                                x2 = size.width / 2,
                                y2 = size.height + height,
                                x3 = size.width,
                                y3 = size.height - height
                            )

                            lineTo(x = size.width, y = 0f)
                            lineTo(x = 0f, y = 0f)
                            close()
                        })
                    }
                }),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            movieDetails.title.uppercase(Locale.getDefault()),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            movieDetails.genres.joinToString(", ") { it.name },
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(
            (movieDetails.vote_average / 2).toFloat(),
            modifier = Modifier.height(20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ExtraDetails(movieDetails = movieDetails)
        if (!movieDetails.overview.isNullOrEmpty() || !movieDetails.tagline.isNullOrEmpty())
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 10.dp,
                    end = 10.dp
                )
            ) {
                if (!movieDetails.tagline.isNullOrEmpty())
                    Text(
                        text = movieDetails.tagline!!,
                        style = MaterialTheme.typography.body1,
                    )
                if (!movieDetails.overview.isNullOrEmpty())
                    Text(
                        text = movieDetails.overview!!,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 5.dp),
                        textAlign = TextAlign.Justify,
                    )
            }
        CastCrew(castCrew = castCrew)
        Recommendations(recommendations = recommendations)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ExtraDetails(movieDetails: MovieDetails) {
    Row() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Year",
                style = MaterialTheme.typography.caption
            )
            Text(
                movieDetails.release_date.split("-").first(),
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Country",
                style = MaterialTheme.typography.caption
            )
            Text(
                movieDetails.production_countries.first().iso_3166_1,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Length",
                style = MaterialTheme.typography.caption
            )
            Text(
                "${movieDetails.runtime} min",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}


@Composable
fun CastCrew(castCrew: CastCrew) {
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = "Cast",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(castCrew.cast.take(10)) { item ->
                CastCrewView(item)
            }
        }
    }
}


@Composable
fun Recommendations(recommendations: HomeMovieList) {
    ListView(
        movieList = recommendations.movieList,
        title = recommendations.title
    )
}

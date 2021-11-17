package com.nividata.movie_time.view.movieDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
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
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.CastCrew
import com.nividata.movie_time.domain.model.ExternalIds
import com.nividata.movie_time.domain.model.HomeMovieList
import com.nividata.movie_time.domain.model.MovieDetails
import com.nividata.movie_time.navigation.Screen
import com.nividata.movie_time.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.nividata.movie_time.view.common.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun MovieDetailsView(
    navController: NavHostController,
    viewModel: MovieDetailsViewModel
) {
    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, _ ->
        viewModel.setEvent(MovieDetailsContract.Event.MovieSelection(id))
    }

    val onMoreIconClicked: (
        categoryType: String,
        categoryName: String
    ) -> Unit = { categoryType, categoryName ->
        viewModel.setEvent(
            MovieDetailsContract.Event.MovieListSelection(
                categoryName = categoryName,
                categoryType = categoryType
            )
        )
    }

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is MovieDetailsContract.Effect.Navigation.ToMovieDetails -> {
                    navController.navigate(Screen.MovieDetail.route(effect.movieId)) {
                        popUpTo(Screen.Main.route)
                    }
                }
                is MovieDetailsContract.Effect.Navigation.ToMovieList -> {
                    navController.navigate(
                        Screen.MovieList.route(
                            id = effect.id,
                            type = effect.type,
                            categoryName = effect.categoryName,
                            categoryType = effect.categoryType
                        )
                    ) {
                        popUpTo(Screen.Main.route)
                    }
                }
            }
        }.collect()
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    when (state) {
        is MovieDetailsContract.State.Loading -> DetailsShimmerView()
        is MovieDetailsContract.State.Success -> {
            WatchListView(
                link = state.watchProviderData.link,
                flatrateList = state.watchProviderData.list,
                coroutineScope = coroutineScope,
                modalBottomSheetState = modalBottomSheetState,
            ) {
                DetailsView(
                    movieDetails = state.movieDetails,
                    castCrew = state.castCrew,
                    recommendations = state.recommendations,
                    similar = state.similar,
                    externalIds = state.externalIds,
                    showPlay = state.watchProviderData.list.isNotEmpty(),
                    onItemClicked = onItemClicked,
                    onMoreIconClicked = onMoreIconClicked,
                    modalBottomSheetState = modalBottomSheetState,
                    coroutineScope = coroutineScope,
                )
            }
        }
        is MovieDetailsContract.State.Failed -> ErrorMessageView(message = state.message)
    }
}

@ExperimentalMaterialApi
@Composable
fun DetailsView(
    movieDetails: MovieDetails,
    castCrew: CastCrew,
    recommendations: HomeMovieList,
    similar: HomeMovieList,
    externalIds: ExternalIds,
    showPlay: Boolean,
    onItemClicked: (Int, String) -> Unit,
    onMoreIconClicked: (String, String) -> Unit,
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box() {
            Surface(
                shape = object : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {
                        val height = 150f
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
                },
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .height(320.dp)
                    .padding(bottom = 20.dp),
            ) {
                Image(
                    painter = rememberImagePainter(Constant.IMAGE_BASE_URL.plus(movieDetails.backdrop_path)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            if (showPlay)
                PlayButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clickable {
                            coroutineScope.launch {
                                if (modalBottomSheetState.isVisible) {
                                    modalBottomSheetState.hide()
                                } else {
                                    modalBottomSheetState.show()
                                }
                            }
                        }
                )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            movieDetails.title.uppercase(Locale.getDefault()),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            movieDetails.genres.joinToString(", ") { it.name },
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(horizontal = 40.dp)
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
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
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
        OtherDetails(movieDetails = movieDetails)
        ProductionCompanyView(productionCountriesList = movieDetails.production_companies)
        ExternalLinkView(externalIds = externalIds)
        Recommendations(
            recommendations = recommendations,
            onItemClicked = onItemClicked,
            onMoreIconClicked = onMoreIconClicked,
        )
        Similar(
            similar = similar,
            onItemClicked = onItemClicked,
            onMoreIconClicked = onMoreIconClicked,
        )
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
        if (movieDetails.production_countries.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)
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
        }
        if (movieDetails.runtime != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)
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
}

@Composable
fun CastCrew(castCrew: CastCrew) {
    if (castCrew.cast.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
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
}

@Composable
fun OtherDetails(
    movieDetails
    : MovieDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Facts",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Original Name",
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = movieDetails.original_title,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.End,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Status",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = movieDetails.status,
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Original Language",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = Locale(movieDetails.original_language).displayName,
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Budget",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = if (movieDetails.budget == 0) "-" else NumberFormat.getCurrencyInstance()
                    .apply {
                        minimumFractionDigits = 0
                    }.format(movieDetails.budget),
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Revenue",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = if (movieDetails.revenue == 0) "-" else NumberFormat.getCurrencyInstance()
                    .apply {
                        minimumFractionDigits = 0
                    }.format(movieDetails.revenue),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun Recommendations(
    recommendations: HomeMovieList,
    onItemClicked: (Int, String) -> Unit,
    onMoreIconClicked: (String, String) -> Unit,
) {
    ListView(
        movieList = recommendations.movieList,
        title = recommendations.title,
        onItemClicked = onItemClicked,
        onMoreIconClicked = onMoreIconClicked,
        categoryType = "recommendations"
    )
}

@Composable
fun Similar(
    similar: HomeMovieList, onItemClicked: (Int, String) -> Unit,
    onMoreIconClicked: (String, String) -> Unit,
) {
    ListView(
        movieList = similar.movieList,
        title = similar.title,
        onItemClicked = onItemClicked,
        onMoreIconClicked = onMoreIconClicked,
        categoryType = "similar"
    )
}

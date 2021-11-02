package com.nividata.owls.view.tvDetails

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
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.model.CastCrew
import com.nividata.owls.domain.model.ExternalIds
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.model.TvDetails
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.nividata.owls.view.common.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun TvDetailsView(
    navController: NavHostController,
    viewModel: TvDetailsViewModel
) {
    val state = viewModel.viewState.value

    val onItemClicked: (id: Int, type: String) -> Unit = { id, _ ->
        viewModel.setEvent(TvDetailsContract.Event.TvSelection(id))
    }

    val onMoreIconClicked: (
        categoryType: String,
        categoryName: String
    ) -> Unit = { categoryType, categoryName ->
        viewModel.setEvent(
            TvDetailsContract.Event.MovieListSelection(
                categoryName = categoryName,
                categoryType = categoryType
            )
        )
    }

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is TvDetailsContract.Effect.Navigation.ToTvDetails -> {
                    navController.navigate(Screen.TvDetail.route(effect.tvId)) {
                        popUpTo(Screen.Main.route)
                    }
                }
                is TvDetailsContract.Effect.Navigation.ToMovieList -> {
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
        is TvDetailsContract.State.Loading -> ProgressView()
        is TvDetailsContract.State.Success -> {
            WatchListView(
                link = state.watchProviderData.link,
                flatrateList = state.watchProviderData.list,
                coroutineScope = coroutineScope,
                modalBottomSheetState = modalBottomSheetState,
            ) {
                DetailsView(
                    tvDetails = state.tvDetails,
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
        is TvDetailsContract.State.Failed -> ErrorMessageView(message = state.message)
    }
}

@ExperimentalMaterialApi
@Composable
fun DetailsView(
    tvDetails: TvDetails,
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
                    painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(tvDetails.backdrop_path)),
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
            tvDetails.name.uppercase(Locale.getDefault()),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            tvDetails.genres.joinToString(", ") { it.name },
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(
            (tvDetails.vote_average / 2).toFloat(),
            modifier = Modifier.height(20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ExtraDetails(tvDetails = tvDetails)
        if (tvDetails.overview.isNotEmpty() || tvDetails.tagline.isNotEmpty())
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 10.dp,
                    end = 10.dp
                )
            ) {
                if (tvDetails.tagline.isNotEmpty()) {
                    Text(
                        text = tvDetails.tagline,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                if (tvDetails.overview.isNotEmpty()) {
                    Text(
                        text = tvDetails.overview,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 5.dp),
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        CastCrew(castCrew = castCrew)
        OtherDetails(tvDetails = tvDetails)
        ProductionCompanyView(productionCountriesList = tvDetails.production_companies)
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
fun OtherDetails(tvDetails: TvDetails) {
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
                text = tvDetails.original_name,
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
                text = tvDetails.status,
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Networks",
                style = MaterialTheme.typography.subtitle2
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(tvDetails.networks) {
                    Image(
                        painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(it.logo_path)),
                        contentDescription = null,
                        modifier = Modifier.height(15.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Type",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = tvDetails.type,
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
                text = Locale(tvDetails.original_language).displayName,
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
                text = "Number Of Episodes",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = tvDetails.number_of_episodes.toString(),
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
                text = "Number Of Seasons",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = tvDetails.number_of_seasons.toString(),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun ExtraDetails(tvDetails: TvDetails) {
    Row() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Year",
                style = MaterialTheme.typography.caption
            )
            Text(
                tvDetails.first_air_date.split("-").first(),
                style = MaterialTheme.typography.subtitle1
            )
        }
        if (tvDetails.production_countries.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)

            ) {
                Text(
                    "Country",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    tvDetails.production_countries.first().iso_3166_1,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        if (tvDetails.episode_run_time.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text(
                    "Length",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    "${tvDetails.episode_run_time.first()} min",
                    style = MaterialTheme.typography.subtitle1
                )
            }
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

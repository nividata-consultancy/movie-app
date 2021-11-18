package com.nividata.movie_time.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.nividata.movie_time.R
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.Movie
import java.util.*
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun SliderView(movieList: List<Movie>, title: String, onItemClicked: (Int, String) -> Unit) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
    )
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
                        start = 0.9f,
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
                .fillMaxWidth(0.8f)
                .height(170.dp)
                .clickable {
                    onItemClicked(movieList[it].id, movieList[it].type)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
        ) {
            Box() {
                Image(
                    painter = rememberImagePainter(Constant.IMAGE_BASE_URL.plus(movieList[it].backdropPath),
                        builder = {
                            placeholder(R.drawable.place_holder)
                            error(R.drawable.place_holder)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = movieList[it].title.uppercase(Locale.getDefault()),
                    fontSize = 16.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
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
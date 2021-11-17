package com.nividata.movie_time.view.amazon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.nividata.movie_time.view.common.ListShimmerView
import com.valentinilk.shimmer.shimmer
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun AmazonShimmerView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .shimmer(),
    ) {
        Spacer(modifier = Modifier.height(43.dp))
        HorizontalPager(
            state = rememberPagerState(
                pageCount = 3,
                infiniteLoop = true,
                initialOffscreenLimit = 3,
            ),
            modifier = Modifier
                .fillMaxWidth(),
            dragEnabled = false,
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
                    .height(170.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp,
                backgroundColor = Color.DarkGray
            ) {}
        }
        Spacer(modifier = Modifier.height(43.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(arrayOf(1, 2, 3, 4)) {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .width(130.dp),
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.DarkGray
                ) {}
            }
        }
        ListShimmerView()
        ListShimmerView()
        ListShimmerView()
    }
}
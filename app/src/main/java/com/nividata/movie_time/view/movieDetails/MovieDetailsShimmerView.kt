package com.nividata.movie_time.view.movieDetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.movie_time.view.common.RatingBar
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun DetailsShimmerView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .shimmer(),
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
                color = Color.DarkGray
            ) {}
            Card(
                backgroundColor = Color.DarkGray,
                shape = CircleShape,
                elevation = 8.dp,
                modifier = Modifier
                    .width(54.dp)
                    .height(54.dp)
                    .align(Alignment.BottomCenter)
            ) {}
        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(
            modifier = Modifier
                .width(150.dp)
                .height(14.dp)
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        RatingBar(
            5f,
            modifier = Modifier.height(20.dp),
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .width(50.dp)
                        .height(28.dp)
                        .background(Color.DarkGray)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .width(50.dp)
                        .height(28.dp)
                        .background(Color.DarkGray)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .width(50.dp)
                        .height(28.dp)
                        .background(Color.DarkGray)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                top = 24.dp,
                start = 10.dp,
                end = 10.dp
            )
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 75.dp)
                    .height(20.dp)
                    .background(Color.DarkGray)
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .height(84.dp)
                    .background(Color.DarkGray)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(43.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(arrayOf(1, 2, 3, 4, 5, 6)) { item ->
                    Column(
                        modifier = Modifier
                            .width(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Card(
                            modifier = Modifier
                                .height(64.dp)
                                .width(64.dp),
                            shape = RoundedCornerShape(32.dp),
                            backgroundColor = Color.DarkGray
                        ) {}
                        Spacer(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(Color.DarkGray)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

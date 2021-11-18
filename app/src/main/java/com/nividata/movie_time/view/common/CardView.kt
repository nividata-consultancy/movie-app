package com.nividata.movie_time.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.nividata.movie_time.R
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.Movie

@Composable
fun CardView(movie: Movie, onItemClicked: (Int, String) -> Unit) {
    Card(
        modifier = Modifier
            .height(140.dp)
            .width(95.dp)
            .clickable {
                onItemClicked(movie.id, movie.type)
            },
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                Constant.IMAGE_BASE_URL.plus(movie.posterPath),
                builder = {
                    placeholder(R.drawable.place_holder)
                    error(R.drawable.place_holder)
                }),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
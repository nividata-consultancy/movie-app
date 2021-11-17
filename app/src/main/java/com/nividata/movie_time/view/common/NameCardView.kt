package com.nividata.movie_time.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.Movie

@Composable
fun NameCardView(movie: Movie, index: Int, onItemClicked: (Int, String) -> Unit) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(130.dp),
        shape = RoundedCornerShape(4.dp),
    ) {
        Column() {
            Image(
                painter = rememberImagePainter(Constant.IMAGE_BASE_URL.plus(movie.backdropPath)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(73.dp)
                    .clickable {
                        onItemClicked(movie.id, movie.type)
                    },
                contentScale = ContentScale.Crop,
            )
            Text(
                text = movie.title,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            )
        }
    }
}
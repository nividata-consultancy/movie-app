package com.nividata.owls.view.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.model.response.MovieResponse
import com.nividata.owls.domain.model.Movie

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
                painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(movie.backdropPath)),
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
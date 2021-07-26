package com.nividata.owls.view.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import com.google.accompanist.coil.rememberCoilPainter
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.model.response.MovieResponse

@Composable
fun UpcomingCardView(movie: MovieResponse) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(movie.backdrop_path)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
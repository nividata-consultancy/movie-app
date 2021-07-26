package com.nividata.owls.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.model.response.MovieResponse

@Composable
fun MovieCardView(movie: MovieResponse) {
    Card(
        modifier = Modifier
            .height(160.dp)
            .width(110.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(movie.poster_path)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
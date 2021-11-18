package com.nividata.movie_time.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.nividata.movie_time.R
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.CastCrew

@Composable
fun CastCrewView(cast: CastCrew.Cast) {
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
        ) {
            Image(
                painter = rememberImagePainter(Constant.IMAGE_BASE_URL.plus(cast.profile_path),
                    builder = {
                        placeholder(R.drawable.place_holder)
                        error(R.drawable.place_holder)
                    }),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = cast.name,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Clip,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
package com.nividata.owls.view.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nividata.owls.R

@Composable
fun PlayButton(modifier: Modifier) {
    Card(
        backgroundColor = Color.White,
        shape = CircleShape,
        elevation = 8.dp,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
            contentDescription = "",
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(12.dp)
                .width(30.dp)
                .height(30.dp)
        )
    }
}
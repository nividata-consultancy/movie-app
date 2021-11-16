package com.nividata.movie_time.view.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorMessageView(message: String) {
    Text(text = message)
}
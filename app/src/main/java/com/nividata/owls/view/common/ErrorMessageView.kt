package com.nividata.owls.view.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorMessageView(message: String) {
    Text(text = message)
}
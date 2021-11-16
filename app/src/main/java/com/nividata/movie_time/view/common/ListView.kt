package com.nividata.movie_time.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nividata.movie_time.R
import com.nividata.movie_time.domain.model.Movie

@Composable
fun ListView(
    movieList: List<Movie>,
    title: String,
    categoryType: String,
    onItemClicked: (Int, String) -> Unit,
    onMoreIconClicked: (String, String) -> Unit,
) {
    if (movieList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp)
                    .clickable {
                        onMoreIconClicked(categoryType, title)
                    }
                    .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary,
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(movieList) {
                    CardView(movie = it, onItemClicked = onItemClicked)
                }
            }
        }
    }
}
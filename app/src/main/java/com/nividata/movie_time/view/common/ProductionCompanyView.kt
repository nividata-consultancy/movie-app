package com.nividata.movie_time.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.model.ProductionCompanies

@Composable
fun ProductionCompanyView(productionCountriesList: List<ProductionCompanies>) {
    if (productionCountriesList.isNotEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Production Company",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(productionCountriesList) { item ->
                    Column(
                        modifier = Modifier
                            .width(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Card(
                            modifier = Modifier
                                .height(48.dp)
                                .width(48.dp),
                            shape = RoundedCornerShape(32.dp),
                        ) {
                            Image(
                                painter = rememberImagePainter(Constant.IMAGE_BASE_URL.plus(item.logo_path)),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit,
                            )
                        }
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}

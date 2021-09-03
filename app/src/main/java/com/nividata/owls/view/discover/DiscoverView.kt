package com.nividata.owls.view.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.model.response.Genres
import com.nividata.owls.domain.model.People

@Composable
fun DiscoverView(
    viewModel: DiscoverViewModel
) {
    val state = viewModel.viewState.value
    val onEventSent: (DiscoverContract.Event) -> Unit = { event -> viewModel.setEvent(event) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        SearchView(state = textState)
        TypeSelectionView(onTypeSelect = { type ->
            onEventSent(
                DiscoverContract.Event.TypeSelection(
                    type
                )
            )
        })
        when (state) {
            is DiscoverContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is DiscoverContract.State.Success -> {
                GenreListView(state.genreList)
                PeopleView(genreList = state.peopleList)
            }
            is DiscoverContract.State.Failed -> Text(text = state.message)
        }
    }
}

@Composable
fun TypeSelectionView(onTypeSelect: (Type) -> Unit) {
    Column() {
        Text(
            "Type",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Button(
                onClick = { onTypeSelect(Type.MOVIE) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff2E2E2E)
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    "Movie",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Button(
                onClick = { onTypeSelect(Type.SHOW) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff2E2E2E)
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    "Show",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun GenreListView(genreList: List<Genres>) {
    Column() {
        Text(
            "Genre",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(genreList) {
                TitleChipView(title = it.name)
            }
        }
    }
}

@Composable
fun TitleChipView(title: String) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xff2E2E2E)
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(
            title,
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}

@Composable
fun PeopleView(genreList: List<People>){
    Column() {
        Text(
            "People",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(genreList) {
                Column() {
                    Card(
                        modifier = Modifier
                            .height(140.dp)
                            .width(95.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Image(
                            painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(it.profile_path)),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Text(it.name)
                }
            }
        }
    }
}
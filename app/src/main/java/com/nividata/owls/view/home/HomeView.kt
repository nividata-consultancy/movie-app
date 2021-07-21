package com.nividata.owls.view.home

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.domain.data.model.response.MovieResponse

@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val notesState = viewModel.notes.collectAsState(initial = null).value

//    val onNoteClicked: (Note) -> Unit = {
//        println("Note Clicked")
//        navController.navigate(Screen.NotesDetail.route(it.id))
//    }

    when (notesState) {
        is ViewState.Loading, null -> CircularProgressIndicator()
        is ViewState.Success -> HomeContent(notesState.data.results)
        is ViewState.Failed -> Text(text = notesState.message)
    }
}

@Composable
fun HomeContent(movieList: List<MovieResponse>) {
    Text(text = movieList.toString())
}
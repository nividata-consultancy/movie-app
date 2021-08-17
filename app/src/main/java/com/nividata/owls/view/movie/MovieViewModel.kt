package com.nividata.owls.view.movie

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<MovieContract.Event,
        MovieContract.State,
        MovieContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): MovieContract.State = MovieContract.State.Loading

    override fun handleEvents(event: MovieContract.Event) {
        when (event) {
            is MovieContract.Event.MovieSelection -> {
                setEffect {
                    MovieContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        val homeMovieList = tmdbRepository.getHomeMovies()
        setState { MovieContract.State.Success(homeMovieList) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
    }
}
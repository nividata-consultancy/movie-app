package com.nividata.owls.view.home

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<HomeContract.Event,
        HomeContract.State,
        HomeContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): HomeContract.State = HomeContract.State.Loading

    override fun handleEvents(event: HomeContract.Event) {

        when (event) {
            is HomeContract.Event.MovieSelection -> {
                setEffect {
                    HomeContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        val categories = tmdbRepository.getHomeMovies()
        setState { HomeContract.State.Success(categories) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
    }
}
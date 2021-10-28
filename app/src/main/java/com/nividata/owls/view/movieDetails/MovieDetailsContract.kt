package com.nividata.owls.view.movieDetails

import com.nividata.owls.domain.core.model.WatchProviderData
import com.nividata.owls.domain.model.CastCrew
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.model.MovieDetails
import com.nividata.owls.domain.model.WatchProviders
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class MovieDetailsContract {
    sealed class Event : ViewEvent {
        data class MovieSelection(val movieId: Int) : Event()
    }

    sealed class State : ViewState {
        data class Success(
            val movieDetails: MovieDetails,
            val castCrew: CastCrew,
            val recommendations: HomeMovieList,
            val watchProviderData: WatchProviderData,
        ) : State()

        object Loading : State()
        data class Failed(val message: String) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToMovieDetails(val movieId: Int) : Navigation()
        }
    }
}

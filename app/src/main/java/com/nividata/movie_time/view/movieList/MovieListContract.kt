package com.nividata.movie_time.view.movieList

import com.nividata.movie_time.domain.core.model.WatchProviderData
import com.nividata.movie_time.domain.model.CastCrew
import com.nividata.movie_time.domain.model.ExternalIds
import com.nividata.movie_time.domain.model.HomeMovieList
import com.nividata.movie_time.domain.model.MovieDetails
import com.nividata.movie_time.view.base.ViewEvent
import com.nividata.movie_time.view.base.ViewSideEffect
import com.nividata.movie_time.view.base.ViewState

sealed class MovieListContract {
    sealed class Event : ViewEvent {
        data class MovieSelection(val id: Int, val type: String) : Event()
    }

    sealed class State : ViewState {
        data class Success(
            val movieDetails: MovieDetails,
            val castCrew: CastCrew,
            val recommendations: HomeMovieList,
            val similar: HomeMovieList,
            val watchProviderData: WatchProviderData,
            val externalIds: ExternalIds,
        ) : State()

        object Loading : State()
        data class Failed(val message: String) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToMovieDetails(val movieId: Int) : Navigation()
            data class ToTvDetails(val tvId: Int) : Navigation()
        }
    }
}

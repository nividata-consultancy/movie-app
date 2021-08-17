package com.nividata.owls.view.discover

import com.nividata.owls.domain.data.model.response.Genres
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

enum class Type { MOVIE, SHOW }

sealed class DiscoverContract {
    sealed class Event : ViewEvent {
        data class MovieSelection(val movieId: String) : Event()
        data class TypeSelection(val type: Type) : Event()
    }


    sealed class State : ViewState {
        data class Success(val genreList: List<Genres> = listOf(), val type: Type = Type.MOVIE) :
            State()

        object Loading : State()
        data class Failed(val message: String) : State()
    }

//    data class State(val categories: List<MovieResponse> = listOf()) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToMovieDetails(val movieId: String) : Navigation()
        }
    }
}

package com.nividata.owls.view.netflix

import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class NetflixContract {
    sealed class Event : ViewEvent {
        data class NetflixSelection(val movieId: String) : Event()
    }


    sealed class State : ViewState {
        data class Success(val homeMovieList: List<HomeMovieList> = listOf()) : State()
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

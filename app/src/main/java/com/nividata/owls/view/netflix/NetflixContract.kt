package com.nividata.owls.view.netflix

import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class NetflixContract {
    sealed class Event : ViewEvent {
        data class NetflixItemSelection(val id: Int, val type: String) : Event()
    }

    sealed class State : ViewState {
        data class Success(val homeMovieList: List<HomeMovieList> = listOf()) : State()
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

package com.nividata.owls.view.tv

import com.nividata.owls.domain.model.HomeTvList
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class TvContract {
    sealed class Event : ViewEvent {
        data class TvSelection(val movieId: String) : Event()
    }


    sealed class State : ViewState {
        data class Success(val homeTvList: List<HomeTvList> = listOf()) : State()
        object Loading : State()
        data class Failed(val message: String) : State()
    }

//    data class State(val categories: List<MovieResponse> = listOf()) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToTvDetails(val movieId: String) : Navigation()
        }
    }
}

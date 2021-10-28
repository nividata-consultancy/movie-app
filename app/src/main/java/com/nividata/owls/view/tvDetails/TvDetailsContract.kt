package com.nividata.owls.view.tvDetails

import com.nividata.owls.domain.model.CastCrew
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.model.TvDetails
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class TvDetailsContract {
    sealed class Event : ViewEvent {
        data class TvSelection(val tvId: Int) : Event()
    }

    sealed class State : ViewState {
        data class Success(
            val tvDetails: TvDetails,
            val castCrew: CastCrew,
            val recommendations: HomeMovieList
        ) : State()

        object Loading : State()
        data class Failed(val message: String) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToTvDetails(val tvId: Int) : Navigation()
        }
    }
}

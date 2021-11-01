package com.nividata.owls.view.tvDetails

import com.nividata.owls.domain.core.model.WatchProviderData
import com.nividata.owls.domain.model.CastCrew
import com.nividata.owls.domain.model.ExternalIds
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.model.TvDetails
import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState
import com.nividata.owls.view.movieDetails.MovieDetailsContract

sealed class TvDetailsContract {
    sealed class Event : ViewEvent {
        data class TvSelection(val tvId: Int) : Event()
        data class MovieListSelection(
            val categoryName: String,
            val categoryType: String
        ) : Event()
    }

    sealed class State : ViewState {
        data class Success(
            val tvDetails: TvDetails,
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
            data class ToTvDetails(val tvId: Int) : Navigation()
            data class ToMovieList(
                val id: Int?,
                val type: String?,
                val categoryName: String,
                val categoryType: String
            ) : Navigation()
        }
    }
}

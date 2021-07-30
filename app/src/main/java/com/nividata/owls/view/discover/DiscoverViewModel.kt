package com.nividata.owls.view.discover

import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<DiscoverContract.Event,
        DiscoverContract.State,
        DiscoverContract.Effect>() {
    override fun setInitialState(): DiscoverContract.State = DiscoverContract.State.Loading

    override fun handleEvents(event: DiscoverContract.Event) {
        when (event) {
            is DiscoverContract.Event.MovieSelection -> {
                setEffect {
                    DiscoverContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

}
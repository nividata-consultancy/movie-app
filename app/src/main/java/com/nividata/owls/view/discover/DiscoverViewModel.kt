package com.nividata.owls.view.discover

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.domain.data.model.response.GenreList
import com.nividata.owls.domain.model.GenreTypeWise
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<DiscoverContract.Event,
        DiscoverContract.State,
        DiscoverContract.Effect>() {
    lateinit var genreTypeWise: GenreTypeWise

    init {
        viewModelScope.launch {
            getInitData()
        }
    }

    override fun setInitialState(): DiscoverContract.State = DiscoverContract.State.Loading

    override fun handleEvents(event: DiscoverContract.Event) {
        when (event) {
            is DiscoverContract.Event.MovieSelection -> {
                setEffect {
                    DiscoverContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
            is DiscoverContract.Event.TypeSelection -> {
                if (event.type == Type.MOVIE)
                    setState {
                        DiscoverContract.State.Success(
                            genreTypeWise.movieGenre,
                            Type.MOVIE
                        )
                    }
                else
                    setState {
                        DiscoverContract.State.Success(
                            genreTypeWise.tvGenre,
                            Type.SHOW
                        )
                    }

            }
        }
    }

    private suspend fun getInitData() {
        genreTypeWise = tmdbRepository.getGenre()
        setState { DiscoverContract.State.Success(genreTypeWise.movieGenre, Type.MOVIE) }
    }

}
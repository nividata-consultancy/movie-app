package com.nividata.owls.view.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieDetailsViewModel @AssistedInject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager,
    @Assisted private val movieId: Int
) : BaseViewModel<MovieDetailsContract.Event,
        MovieDetailsContract.State,
        MovieDetailsContract.Effect>() {

    init {
        viewModelScope.launch {
            getMovieDetails()
        }
    }

    override fun setInitialState(): MovieDetailsContract.State = MovieDetailsContract.State.Loading

    override fun handleEvents(event: MovieDetailsContract.Event) {
        when (event) {
            is MovieDetailsContract.Event.MovieSelection -> {
                setEffect {
                    MovieDetailsContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getMovieDetails() {
        val homeMovieList = tmdbRepository.getMovieDetails(movieId)
        val castCrew = tmdbRepository.getMovieCastCrew(movieId)
        val recommendations = tmdbRepository.getMovieRecommendations(movieId)
        val watchProviderData = tmdbRepository.getMovieWatchProviders(movieId)
        val similar = tmdbRepository.getMovieSimilar(movieId)
        val externalIds = tmdbRepository.getMovieExternalIds(movieId)
        setState {
            MovieDetailsContract.State.Success(
                homeMovieList,
                castCrew,
                recommendations,
                similar,
                watchProviderData,
                externalIds,
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Int): MovieDetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            movieId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(movieId) as T
            }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule
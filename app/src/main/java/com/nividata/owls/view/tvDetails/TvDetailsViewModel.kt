package com.nividata.owls.view.tvDetails

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
class TvDetailsViewModel @AssistedInject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager,
    @Assisted private val tvId: Int
) : BaseViewModel<TvDetailsContract.Event,
        TvDetailsContract.State,
        TvDetailsContract.Effect>() {

    init {
        viewModelScope.launch {
            getMovieDetails()
        }
    }

    override fun setInitialState(): TvDetailsContract.State = TvDetailsContract.State.Loading

    override fun handleEvents(event: TvDetailsContract.Event) {
        when (event) {
            is TvDetailsContract.Event.TvSelection -> {
                setEffect {
                    TvDetailsContract.Effect.Navigation.ToTvDetails(event.tvId)
                }
            }
        }
    }

    private suspend fun getMovieDetails() {
        val homeMovieList = tmdbRepository.getTvDetails(tvId)
        val castCrew = tmdbRepository.getTvCastCrew(tvId)
        val recommendations = tmdbRepository.getTvRecommendations(tvId)
        setState { TvDetailsContract.State.Success(homeMovieList, castCrew, recommendations) }
    }

    @AssistedFactory
    interface Factory {
        fun create(tvId: Int): TvDetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            tvId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(tvId) as T
            }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule
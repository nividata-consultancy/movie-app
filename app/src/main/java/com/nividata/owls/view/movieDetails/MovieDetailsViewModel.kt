package com.nividata.owls.view.movieDetails

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager,
    /*@Assisted*/ /*private val movieId: Int=71446*/
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
        val homeMovieList = tmdbRepository.getMovieDetails(76341)
        val castCrew = tmdbRepository.getCastCrew(76341)
        val recommendations = tmdbRepository.getMovieRecommendations(76341)
        setState { MovieDetailsContract.State.Success(homeMovieList, castCrew, recommendations) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
    }

//    @AssistedFactory
//    interface Factory {
//        fun create(movieId: Int): MovieDetailsViewModel
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    companion object {
//        fun provideFactory(
//            assistedFactory: Factory,
//            movieId: Int
//        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return assistedFactory.create(movieId) as T
//            }
//        }
//    }

}
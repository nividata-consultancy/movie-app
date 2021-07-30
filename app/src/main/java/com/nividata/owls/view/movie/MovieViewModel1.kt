package com.nividata.owls.view.movie

import androidx.lifecycle.ViewModel
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MovieViewModel1 @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

//    val upcomingMovies: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getUpcomingMovies()
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
//
//    val availableMovies: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getAvailableMovies()
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
//
//    val trendingMovies: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getTrendingMovies()
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
//
//    val popularMoviesOnNetflix: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getPopularMoviesByNetwork(networks = "213")
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
//
//    val popularMoviesOnAmazon: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getPopularMoviesByNetwork(networks = "1024")
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
//
//    val popularMoviesOnHotstar: SharedFlow<ViewState<MovieListResponse>> =
//        tmdbRepository.getPopularMoviesByNetwork(networks = "3919")
//            .distinctUntilChanged()
//            .map { result ->
//                when (result) {
//                    is ResponseResult.Success -> ViewState.success(result.data)
//                    is ResponseResult.Error ->
//                        ViewState.failed(result.message)
//                }
//            }.onStart { emit(ViewState.loading()) }
//            .shareWhileObserved(viewModelScope)
}
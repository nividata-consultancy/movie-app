package com.nividata.owls.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.ResponseResult
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.extention.shareWhileObserved
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    val upcomingMovies: SharedFlow<ViewState<MovieListResponse>> = tmdbRepository.getUpcomingMovies()
        .distinctUntilChanged()
        .map { result ->
            when (result) {
                is ResponseResult.Success -> ViewState.success(result.data)
                is ResponseResult.Error ->
                    ViewState.failed(result.message)
            }
        }.onStart { emit(ViewState.loading()) }
        .shareWhileObserved(viewModelScope)

    val availableMovies: SharedFlow<ViewState<MovieListResponse>> = tmdbRepository.getAvailableMovies()
        .distinctUntilChanged()
        .map { result ->
            when (result) {
                is ResponseResult.Success -> ViewState.success(result.data)
                is ResponseResult.Error ->
                    ViewState.failed(result.message)
            }
        }.onStart { emit(ViewState.loading()) }
        .shareWhileObserved(viewModelScope)
}
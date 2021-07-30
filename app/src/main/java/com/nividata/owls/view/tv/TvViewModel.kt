package com.nividata.owls.view.tv

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<TvContract.Event,
        TvContract.State,
        TvContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): TvContract.State = TvContract.State.Loading

    override fun handleEvents(event: TvContract.Event) {

        when (event) {
            is TvContract.Event.TvSelection -> {
                setEffect {
                    TvContract.Effect.Navigation.ToTvDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        val homeTvList = tmdbRepository.getHomeTv()
        setState { TvContract.State.Success(homeTvList) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
    }
}
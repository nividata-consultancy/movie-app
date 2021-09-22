package com.nividata.owls.view.hotstar

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotstarViewModel @Inject constructor(
    private val owlsRepository: OwlsRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<HotstarContract.Event,
        HotstarContract.State,
        HotstarContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): HotstarContract.State = HotstarContract.State.Loading

    override fun handleEvents(event: HotstarContract.Event) {
        when (event) {
            is HotstarContract.Event.NetflixSelection -> {
                setEffect {
                    HotstarContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        try {
            val homeMovieList = owlsRepository.getHotstarData()
            setState { HotstarContract.State.Success(homeMovieList) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
        } catch (e: Exception) {
            Log.e("ok", e.toString())
        }
    }
}
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
            getHotstarData()
        }
    }

    override fun setInitialState(): HotstarContract.State = HotstarContract.State.Loading

    override fun handleEvents(event: HotstarContract.Event) {
        when (event) {
            is HotstarContract.Event.HotstarItemSelection -> {
                if (event.type == "movie") {
                    setEffect {
                        HotstarContract.Effect.Navigation.ToMovieDetails(event.id)
                    }
                } else {
                    setEffect {
                        HotstarContract.Effect.Navigation.ToTvDetails(event.id)
                    }
                }
            }
            is HotstarContract.Event.MovieListSelection -> {
                setEffect {
                    HotstarContract.Effect.Navigation.ToMovieList(
                        categoryName = event.categoryName,
                        categoryType = event.categoryType
                    )
                }
            }
        }
    }

    private suspend fun getHotstarData() {
        try {
            val homeMovieList = owlsRepository.getHotstarData()
            setState { HotstarContract.State.Success(homeMovieList) }
        } catch (e: Exception) {
            Log.e("ok", e.toString())
        }
    }
}
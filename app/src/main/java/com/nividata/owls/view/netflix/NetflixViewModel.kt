package com.nividata.owls.view.netflix

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetflixViewModel @Inject constructor(
    private val owlsRepository: OwlsRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<NetflixContract.Event, NetflixContract.State, NetflixContract.Effect>() {

    init {
        viewModelScope.launch {
            getNetflixData()
        }
    }

    override fun setInitialState(): NetflixContract.State = NetflixContract.State.Loading

    override fun handleEvents(event: NetflixContract.Event) {
        when (event) {
            is NetflixContract.Event.NetflixItemSelection -> {
                if (event.type == "movie") {
                    setEffect {
                        NetflixContract.Effect.Navigation.ToMovieDetails(event.id)
                    }
                } else {
                    setEffect {
                        NetflixContract.Effect.Navigation.ToTvDetails(event.id)
                    }
                }
            }
        }
    }

    private suspend fun getNetflixData() {
        try {
            val homeMovieList = owlsRepository.getNetflixData()
            setState { NetflixContract.State.Success(homeMovieList) }
        } catch (e: Exception) {
            Log.e("ok", e.toString())
        }
    }
}
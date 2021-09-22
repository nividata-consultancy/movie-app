package com.nividata.owls.view.netflix

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetflixViewModel @Inject constructor(
    private val owlsRepository: OwlsRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<NetflixContract.Event,
        NetflixContract.State,
        NetflixContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): NetflixContract.State = NetflixContract.State.Loading

    override fun handleEvents(event: NetflixContract.Event) {
        when (event) {
            is NetflixContract.Event.NetflixSelection -> {
                setEffect {
                    NetflixContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        try{
        val homeMovieList = owlsRepository.getNetflixData()
        setState { NetflixContract.State.Success(homeMovieList) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
        }catch (e:Exception){
            Log.e("ok",e.toString())
        }
    }
}
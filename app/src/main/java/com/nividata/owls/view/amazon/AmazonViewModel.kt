package com.nividata.owls.view.amazon

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmazonViewModel @Inject constructor(
    private val owlsRepository: OwlsRepository,
    private val sessionManager: SessionManager
) : BaseViewModel<AmazonContract.Event,
        AmazonContract.State,
        AmazonContract.Effect>() {

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

    override fun setInitialState(): AmazonContract.State = AmazonContract.State.Loading

    override fun handleEvents(event: AmazonContract.Event) {
        when (event) {
            is AmazonContract.Event.NetflixSelection -> {
                setEffect {
                    AmazonContract.Effect.Navigation.ToMovieDetails(event.movieId)
                }
            }
        }
    }

    private suspend fun getFoodCategories() {
        try{
        val homeMovieList = owlsRepository.getAmazonData()
        setState { AmazonContract.State.Success(homeMovieList) }
//        setEffect { FoodCategoriesContract.Effect.ToastDataWasLoaded }
        }catch (e:Exception){
            Log.e("ok",e.toString())
        }
    }
}
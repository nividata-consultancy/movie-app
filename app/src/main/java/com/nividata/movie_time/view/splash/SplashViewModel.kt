package com.nividata.movie_time.view.splash

import androidx.lifecycle.viewModelScope
import com.nividata.movie_time.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    init {
        viewModelScope.launch {
            saveToken()
        }
    }

    override fun setInitialState(): SplashContract.State = SplashContract.State.Loading

    override fun handleEvents(event: SplashContract.Event) {}

    private suspend fun saveToken() {
        delay(2000L)
        setState { SplashContract.State.Success(true) }
        setEffect { SplashContract.Effect.Navigation.ToMain }
    }
}

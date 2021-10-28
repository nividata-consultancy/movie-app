package com.nividata.owls.view.splash

import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    init {
        viewModelScope.launch {
            saveToken()
        }
    }

    override fun setInitialState(): SplashContract.State = SplashContract.State.Loading

    override fun handleEvents(event: SplashContract.Event) {}

    private suspend fun saveToken() {
        sessionManager.saveToken("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDFjYjI2MTNkOGYxODE5MDAyODkyODIwYzQ5Yzg4ZCIsInN1YiI6IjYwZTU3MWM0ODNlZTY3MDA1ZGU1ZWYxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AoAnnujUJbVnstTL1ykfcLElvAoruXbVi-2JWMXC0gA")
        delay(50L)
        setState { SplashContract.State.Success(true) }
        setEffect { SplashContract.Effect.Navigation.ToMain }
    }
}

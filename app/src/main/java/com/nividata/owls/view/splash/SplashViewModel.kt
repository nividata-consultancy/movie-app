package com.nividata.owls.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.extention.shareWhileObserved
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _authFlow = MutableSharedFlow<ViewState<Boolean>>()
    val authFlow: SharedFlow<ViewState<Boolean>> = _authFlow.shareWhileObserved(viewModelScope)

    init {
        viewModelScope.launch {
            saveToken("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDFjYjI2MTNkOGYxODE5MDAyODkyODIwYzQ5Yzg4ZCIsInN1YiI6IjYwZTU3MWM0ODNlZTY3MDA1ZGU1ZWYxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AoAnnujUJbVnstTL1ykfcLElvAoruXbVi-2JWMXC0gA")
            delay(50L)
            _authFlow.emit(ViewState.success(true))
        }
    }

    private fun saveToken(token: String) {
        sessionManager.saveToken(token)
    }
}

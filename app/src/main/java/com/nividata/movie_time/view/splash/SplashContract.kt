package com.nividata.movie_time.view.splash

import com.nividata.movie_time.view.base.ViewEvent
import com.nividata.movie_time.view.base.ViewSideEffect
import com.nividata.movie_time.view.base.ViewState

sealed class SplashContract {
    sealed class Event : ViewEvent

    sealed class State : ViewState {
        data class Success(
            val isLoggedIn: Boolean
        ) : State()

        object Loading : State()
        data class Failed(val message: String) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToMain : Navigation()
        }
    }
}

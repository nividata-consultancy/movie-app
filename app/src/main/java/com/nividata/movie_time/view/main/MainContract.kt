package com.nividata.movie_time.view.main

import com.nividata.movie_time.view.base.ViewEvent
import com.nividata.movie_time.view.base.ViewSideEffect
import com.nividata.movie_time.view.base.ViewState

sealed class MainContract {
    sealed class Event : ViewEvent {
        data class ChangeTheme(val index: Int) : Event()
    }

    sealed class State : ViewState {
        object Loading : State()
    }

    sealed class Effect : ViewSideEffect
}

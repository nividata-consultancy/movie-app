package com.nividata.owls.view.main

import com.nividata.owls.view.base.ViewEvent
import com.nividata.owls.view.base.ViewSideEffect
import com.nividata.owls.view.base.ViewState

sealed class MainContract {
    sealed class Event : ViewEvent {
        data class ChangeTheme(val index: Int) : Event()
    }

    sealed class State : ViewState {
        object Loading : State()
    }

    sealed class Effect : ViewSideEffect
}

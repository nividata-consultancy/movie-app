package com.nividata.owls.view.main

import com.nividata.owls.di.ThemeManager
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeManager: ThemeManager
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun setInitialState(): MainContract.State = MainContract.State.Loading

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.ChangeTheme -> {
                when (event.index) {
                    0 -> {
                        themeManager.changeTheme(Screen.Netflix.route)
                    }
                    1 -> {
                        themeManager.changeTheme(Screen.Amazon.route)
                    }
                    else -> {
                        themeManager.changeTheme(Screen.Hotstar.route)
                    }
                }
            }
        }
    }
}

package com.nividata.movie_time.di

import com.nividata.movie_time.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ThemeManager {
    val themeType = MutableStateFlow(Screen.Netflix.route)


    fun changeTheme(type: String) {
        themeType.update { type }
    }
}

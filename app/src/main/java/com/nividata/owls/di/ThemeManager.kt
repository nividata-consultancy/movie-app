package com.nividata.owls.di

import com.nividata.owls.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ThemeManager {
    val themeType = MutableStateFlow(Screen.Netflix.route)


    fun changeTheme(type: String) {
        themeType.update { type }
    }
}

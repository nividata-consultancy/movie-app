package com.nividata.owls.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nividata.owls.navigation.Screen

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Red,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Color.Red,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val NetflixColorPalette = darkColors(
    primary = Netflix,
    primaryVariant = Color.Black,
    secondary = Netflix,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val AmazonColorPalette = darkColors(
    primary = Amazon,
    primaryVariant = Color.Black,
    secondary = Amazon,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val HotstarColorPalette = darkColors(
    primary = Hotstar,
    primaryVariant = Color.Black,
    secondary = Hotstar,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun OwlsTheme(themeType: String, content: @Composable() () -> Unit) {
    val colors = when (themeType) {
        Screen.Netflix.route -> NetflixColorPalette
        Screen.Amazon.route -> AmazonColorPalette
        Screen.Hotstar.route -> HotstarColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
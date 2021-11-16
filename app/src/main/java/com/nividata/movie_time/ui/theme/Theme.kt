package com.nividata.movie_time.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.nividata.movie_time.navigation.Screen

private val NetflixLightColorPalette = lightColors(
    primary = Netflix,
    onPrimary = NetflixBlack,
//    primaryVariant = Color.Black,
    secondary = Netflix,
    onSecondary = NetflixBlack,
    background = NetflixWhite,
    onBackground = NetflixBlack,
    surface = NetflixWhite,
    onSurface = NetflixBlack,
    error = NetflixWhite,
    onError = NetflixBlack,
)


private val NetflixDarkColorPalette = darkColors(
    primary = Netflix,
    onPrimary = NetflixWhite,
//    primaryVariant = Color.Black,
    secondary = Netflix,
    onSecondary = NetflixWhite,
    background = NetflixBlack,
    onBackground = NetflixWhite,
    surface = NetflixBlack,
    onSurface = NetflixWhite,
    error = NetflixBlack,
    onError = NetflixWhite,
)

private val AmazonDarkColorPalette = darkColors(
    primary = Amazon,
    onPrimary = AmazonWhite,
//    primaryVariant = Color.Black,
    secondary = Amazon,
    onSecondary = AmazonWhite,
    background = AmazonBlack,
    onBackground = AmazonWhite,
    surface = AmazonBlack,
    onSurface = AmazonWhite,
    error = AmazonBlack,
    onError = AmazonWhite,
)

private val HotstarDarkColorPalette = darkColors(
    primary = Hotstar,
    onPrimary = HotstarWhite,
    //    primaryVariant = Color.Black,
    secondary = Hotstar,
    onSecondary = HotstarWhite,
    background = HotstarBlack,
    onBackground = HotstarWhite,
    surface = HotstarBlack,
    onSurface = HotstarWhite,
    error = HotstarBlack,
    onError = HotstarWhite,
)

@Composable
fun OwlsTheme(themeType: String, content: @Composable() () -> Unit) {
    val colors = when (themeType) {
        Screen.Netflix.route -> NetflixDarkColorPalette
        Screen.Amazon.route -> AmazonDarkColorPalette
        else -> HotstarDarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
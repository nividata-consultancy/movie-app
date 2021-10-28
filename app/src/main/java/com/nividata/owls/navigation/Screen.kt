package com.nividata.owls.navigation

import com.nividata.owls.R

sealed class Screen(val route: String, val name: String, val icon: Int = 0) {
    object Splash : Screen("splash", "Splash")
    object Main : Screen("Main", "main")
    object NewMain : Screen("NewMain", "New Main")
    object Netflix : Screen("Netflix", "Netflix", icon = R.drawable.ic_netflix)
    object Amazon : Screen("Amazon", "Amazon", icon = R.drawable.ic_amazon_prime)
    object Hotstar : Screen("Hotstar", "Hotstar", icon = R.drawable.ic_disney_plus_hotstar)
    object Movie : Screen("Movie", "movie", icon = R.drawable.ic_baseline_local_movies_24)
    object Tv : Screen("Tv", "tv", icon = R.drawable.ic_baseline_tv_24)
    object Discover : Screen("Discover", "descover", icon = R.drawable.ic_baseline_blur_on_24)
    object SignUp : Screen("signup", "Sign Up")
    object Login : Screen("login", "Login")
    object Notes : Screen("movieDetails", "movieDetails")

    object MovieDetail : Screen("movieDetails/{movieId}", "Movie Details") {
        fun route(movieId: Int) = "movieDetails/$movieId"

        const val ARG_MOVIE_ID: String = "movieId"
    }

    object TvDetail : Screen("tvDetails/{tvId}", "Tv Details") {
        fun route(tvId: Int) = "tvDetails/$tvId"

        const val ARG_TV_ID: String = "tvId"
    }

    object AddNote : Screen("note/new", "New note")
    object About : Screen("about", "About")
}

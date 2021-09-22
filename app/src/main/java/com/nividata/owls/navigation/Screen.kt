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
    object Notes : Screen("notes", "Notes")
    object NotesDetail : Screen("note/{noteId}", "Note details") {
        fun route(noteId: String) = "note/$noteId"

        const val ARG_NOTE_ID: String = "noteId"
    }

    object AddNote : Screen("note/new", "New note")
    object About : Screen("about", "About")
}

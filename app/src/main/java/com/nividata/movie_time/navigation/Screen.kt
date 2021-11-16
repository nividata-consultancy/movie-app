package com.nividata.movie_time.navigation

import androidx.compose.ui.graphics.Color
import com.nividata.movie_time.R

sealed class Screen(
    val route: String,
    val name: String,
    val icon: Int = 0,
    val selectedContentColor: Color? = null,
    val unselectedContentColor: Color? = null,
) {
    object Splash : Screen("splash", "Splash")
    object Main : Screen("Main", "Main")
    object Netflix : Screen(
        "Netflix",
        "Netflix",
        icon = R.drawable.ic_netflix,
        selectedContentColor = Color(0xffd81f26),
    )

    object Amazon : Screen(
        "Amazon",
        "Amazon",
        icon = R.drawable.ic_amazon_prime_light,
        selectedContentColor = Color(0xff00a8e1),
    )

    object Hotstar : Screen(
        "Hotstar",
        "Hotstar",
        icon = R.drawable.ic_disney_plus_hotstar_light,
        selectedContentColor = Color(0xff101c50),
    )

    object MovieDetail : Screen("movieDetails/{movieId}", "Movie Details") {
        fun route(movieId: Int) = "movieDetails/$movieId"
        const val ARG_MOVIE_ID: String = "movieId"
    }

    object TvDetail : Screen("tvDetails/{tvId}", "Tv Details") {
        fun route(tvId: Int) = "tvDetails/$tvId"
        const val ARG_TV_ID: String = "tvId"
    }

    object MovieList : Screen(
        "movieList/{categoryType}?type={type}&id={id}&categoryName={categoryName}",
        "Movie List"
    ) {
        fun route(
            id: Int? = null,
            type: String? = null,
            categoryType: String,
            categoryName: String
        ) =
            "movieList/$categoryType?categoryName=$categoryName${if (type == null) "" else "&type=$type"}${if (id == null) "" else "&id=$id"}"

        const val ARG_ID: String = "id"
        const val ARG_TYPE: String = "type"
        const val ARG_CATEGORY_TYPE: String = "categoryType"
        const val ARG_CATEGORY_NAME: String = "categoryName"
    }
}

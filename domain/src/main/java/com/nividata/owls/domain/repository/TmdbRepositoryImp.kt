package com.nividata.owls.domain.repository

import com.nividata.owls.domain.core.model.WatchProviderData
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.data.api.TmdbService
import com.nividata.owls.domain.data.model.response.GenreList
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.domain.data.model.response.TvListResponse
import com.nividata.owls.domain.data.util.getResponse
import com.nividata.owls.domain.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of data of noty user.
 */
@ExperimentalCoroutinesApi
@Singleton
class TmdbRepositoryImp @Inject internal constructor(
    private val tmdbService: TmdbService
) : TmdbRepository {
    override suspend fun getHomeMovies(): List<HomeMovieList> {
        val list = arrayListOf<HomeMovieList>()
        val availableMovie = getAvailableMovies()
        list.add(
            HomeMovieList(
                id = 0,
                title = "Available now",
                movieList = availableMovie.results.map { it.toMovie() }
            )
        )
        val trendingMovie = getTrendingMovies()
        list.add(
            HomeMovieList(
                id = 1,
                title = "Trending",
                movieList = trendingMovie.results.map { it.toMovie() }
            )
        )
        val upcomingMovie = getUpcomingMovies()
        list.add(
            HomeMovieList(
                id = 2,
                title = "Upcoming",
                movieList = upcomingMovie.results.map { it.toMovie() }
            )
        )

        val popularMovie = getPopularMovies()
        list.add(
            HomeMovieList(
                id = 3,
                title = "Popular",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )

//        val kidsMovie = getPopularMovies()
        list.add(
            HomeMovieList(
                id = 4,
                title = "Kids",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )

        list.add(
            HomeMovieList(
                id = 5,
                title = "Action",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )
        list.add(
            HomeMovieList(
                id = 6,
                title = "Comedy",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )
        list.add(
            HomeMovieList(
                id = 7,
                title = "Thriller",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )
        list.add(
            HomeMovieList(
                id = 7,
                title = "Crime",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )
        list.add(
            HomeMovieList(
                id = 7,
                title = "Family",
                movieList = popularMovie.results.map { it.toMovie() }
            )
        )

//        val popularMoviesOnNetflix = getPopularMoviesByNetwork(networks = "213")
//        list.add(
//            HomeMovieList(
//                id = 3,
//                title = "Netflix",
//                movieList = popularMoviesOnNetflix.results
//            )
//        )
//        val popularMoviesOnAmazon = getPopularMoviesByNetwork(networks = "1024")
//        list.add(
//            HomeMovieList(
//                id = 4,
//                title = "Amazon Prime",
//                movieList = popularMoviesOnAmazon.results
//            )
//        )
//        val popularMoviesOnHotstar = getPopularMoviesByNetwork(networks = "3919")
//        list.add(
//            HomeMovieList(
//                id = 5,
//                title = "Disney+ Hotstar",
//                movieList = popularMoviesOnHotstar.results
//            )
//        )
        return list
    }

    override suspend fun getUpcomingMovies(): MovieListResponse {
        return tmdbService.getUpcomingMovies().getResponse()
    }

    override suspend fun getAvailableMovies(): MovieListResponse {
        return tmdbService.getAvailableMovies().getResponse()
    }

    override suspend fun getTrendingMovies(): MovieListResponse {
        return tmdbService.getTrendingMovies().getResponse()
    }


    override suspend fun getPopularMovies(): MovieListResponse {
        return tmdbService.getPopularMovies().getResponse()
    }

    override suspend fun getHomeTv(): List<HomeTvList> {
        val list = arrayListOf<HomeTvList>()
        val upcomingMovie = getPopularTv()
        list.add(
            HomeTvList(
                id = 0,
                title = "Popular",
                tvList = upcomingMovie.results.map { it.toMovie() }
            )
        )
//        val availableMovie = getAvailableMovies()
//        list.add(
//            HomeTvList(
//                id = 1,
//                title = "Available now",
//                tvList = availableMovie.results
//            )
//        )
//        val trendingMovie = getTrendingMovies()
//        list.add(
//            HomeTvList(
//                id = 2,
//                title = "Trending",
//                tvList = trendingMovie.results
//            )
//        )
        val popularMoviesOnNetflix = getPopularMoviesByNetwork(networks = "213")
        list.add(
            HomeTvList(
                id = 3,
                title = "Netflix",
                tvList = popularMoviesOnNetflix.results.map { it.toMovie() }
            )
        )
        val popularMoviesOnAmazon = getPopularMoviesByNetwork(networks = "1024")
        list.add(
            HomeTvList(
                id = 4,
                title = "Amazon Prime",
                tvList = popularMoviesOnAmazon.results.map { it.toMovie() }
            )
        )
        val popularMoviesOnHotstar = getPopularMoviesByNetwork(networks = "3919")
        list.add(
            HomeTvList(
                id = 5,
                title = "Disney+ Hotstar",
                tvList = popularMoviesOnHotstar.results.map { it.toMovie() }
            )
        )
        return list
    }

    override suspend fun getPopularTv(): TvListResponse {
        return tmdbService.getPopularTv().getResponse()
    }

    override suspend fun getPopularMoviesByNetwork(networks: String): TvListResponse {
        return tmdbService.getPopularTvByNetwork(
            options = hashMapOf(
                "with_networks" to networks,
                "release_date.lte" to "2022-01-26",
                "with_original_language" to "hi",
                "sort_by" to "popularity.desc",
                "certification_country" to "IN",
                "ott_region" to "IN"
            )
        ).getResponse()
    }

    override suspend fun getMovieGenre(): GenreList {
        return tmdbService.getMovieGenre().getResponse()
    }

    override suspend fun getTvGenre(): GenreList {
        return tmdbService.getTvGenre().getResponse()
    }

    override suspend fun getGenre(): GenreTypeWise {
        val movieGenreList = getMovieGenre()
        val tvGenreList = getTvGenre()
        return GenreTypeWise(movieGenre = movieGenreList.genres, tvGenre = tvGenreList.genres)
    }

    override suspend fun getPeople(): PeopleList {
        return tmdbService.getPeople().getResponse()
    }


    override suspend fun getMovieDetails(id: Int): MovieDetails {
        return tmdbService.getMovieDetails(id).getResponse()
    }

    override suspend fun getMovieCastCrew(id: Int): CastCrew {
        return tmdbService.getMovieCastCrew(id).getResponse()
    }

    override suspend fun getMovieRecommendations(id: Int): HomeMovieList {
        val recommendations =
            tmdbService.getMovieRecommendations(id, mapOf("param" to 1)).getResponse()
        return HomeMovieList(
            id = 1,
            title = "Recommendations",
            movieList = recommendations.results.map { it.toMovie() }
        )
    }

    override suspend fun getMovieSimilar(id: Int): HomeMovieList {
        return try {
            val similar =
                tmdbService.getMovieSimilar(id, mapOf("param" to 1)).getResponse()
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = similar.results.map { it.toMovie() }
            )
        } catch (e: Exception) {
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = emptyList()
            )
        }
    }

    override suspend fun getMovieExternalIds(id: Int): ExternalIds {
        return tmdbService.getMovieExternalIds(id).getResponse()
    }

    override suspend fun getMovieWatchProviders(id: Int): WatchProviderData {
        val watchProviders = tmdbService.getMovieWatchProviders(id).getResponse()
        val list = arrayListOf<WatchProviders.Results.IN.Flatrate>()
        if (watchProviders.results.iN != null) {
            if (!watchProviders.results.iN.buy.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.buy)
            }
            if (!watchProviders.results.iN.flatrate.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.flatrate)
            }
            if (!watchProviders.results.iN.rent.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.rent)
            }
            return WatchProviderData(
                link = watchProviders.results.iN.link,
                list = list
            )
        } else {
            return WatchProviderData(
                link = "",
                list = list
            )
        }
    }

    override suspend fun getTvDetails(id: Int): TvDetails {
        return tmdbService.getTvDetails(id).getResponse()
    }

    override suspend fun getTvCastCrew(id: Int): CastCrew {
        return tmdbService.getTvCastCrew(id).getResponse()
    }

    override suspend fun getTvRecommendations(id: Int): HomeMovieList {
        val recommendations =
            tmdbService.getTvRecommendations(id, mapOf("param" to 1)).getResponse()
        return HomeMovieList(
            id = 1,
            title = "Recommendations",
            movieList = recommendations.results.map { it.toMovie() }
        )
    }

    override suspend fun getTvSimilar(id: Int): HomeMovieList {
        return try {
            val similar =
                tmdbService.getMovieSimilar(id, mapOf("param" to 1)).getResponse()
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = similar.results.map { it.toMovie() }
            )
        } catch (e: Exception) {
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = emptyList()
            )
        }
    }

    override suspend fun getTvExternalIds(id: Int): ExternalIds {
        return tmdbService.getTvExternalIds(id).getResponse()
    }

    override suspend fun getTvWatchProviders(id: Int): WatchProviderData {
        val watchProviders = tmdbService.getTvWatchProviders(id).getResponse()
        val list = arrayListOf<WatchProviders.Results.IN.Flatrate>()
        if (watchProviders.results.iN != null) {
            if (!watchProviders.results.iN.buy.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.buy)
            }
            if (!watchProviders.results.iN.flatrate.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.flatrate)
            }
            if (!watchProviders.results.iN.rent.isNullOrEmpty()) {
                list.addAll(watchProviders.results.iN.rent)
            }
            return WatchProviderData(
                link = watchProviders.results.iN.link,
                list = list
            )
        } else {
            return WatchProviderData(
                link = "",
                list = list
            )
        }
    }
}

package com.nividata.owls.domain.repository

import com.nividata.owls.domain.HomeMovieList
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.data.api.TmdbService
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.domain.data.util.getResponse
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
        val upcomingMovie = getUpcomingMovies()
        list.add(
            HomeMovieList(
                id = 0,
                title = "Upcoming",
                movieList = upcomingMovie.results
            )
        )
        val availableMovie = getAvailableMovies()
        list.add(
            HomeMovieList(
                id = 1,
                title = "Available now",
                movieList = availableMovie.results
            )
        )
        val trendingMovie = getTrendingMovies()
        list.add(
            HomeMovieList(
                id = 2,
                title = "Trending",
                movieList = trendingMovie.results
            )
        )
        val popularMoviesOnNetflix = getPopularMoviesByNetwork(networks = "213")
        list.add(
            HomeMovieList(
                id = 3,
                title = "Netflix",
                movieList = popularMoviesOnNetflix.results
            )
        )
        val popularMoviesOnAmazon = getPopularMoviesByNetwork(networks = "1024")
        list.add(
            HomeMovieList(
                id = 4,
                title = "Amazon Prime",
                movieList = popularMoviesOnAmazon.results
            )
        )
        val popularMoviesOnHotstar = getPopularMoviesByNetwork(networks = "3919")
        list.add(
            HomeMovieList(
                id = 5,
                title = "Disney+ Hotstar",
                movieList = popularMoviesOnHotstar.results
            )
        )
        return list
    }

    override suspend fun getUpcomingMovies(): MovieListResponse {
        return tmdbService.getUpcomingMovies().getResponse()
    }

    override suspend fun getAvailableMovies(): MovieListResponse {
        return tmdbService.getAvailableMovies().getResponse()
    }

    override suspend fun getTrendingMovies(): MovieListResponse{
        return tmdbService.getTrendingMovies().getResponse()
    }

    override suspend fun getPopularMoviesByNetwork(networks: String): MovieListResponse{
        return tmdbService.getPopularMoviesByNetwork(
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
}

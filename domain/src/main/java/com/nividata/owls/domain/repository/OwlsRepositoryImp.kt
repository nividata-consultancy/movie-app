package com.nividata.owls.domain.repository

import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.model.HomeMovieList
import com.nividata.owls.domain.data.api.OwlsService
import com.nividata.owls.domain.data.util.getResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of data of noty user.
 */
@ExperimentalCoroutinesApi
@Singleton
class OwlsRepositoryImp @Inject internal constructor(
    private val tmdbService: OwlsService
) : OwlsRepository {
    override suspend fun getNetflixData(): List<HomeMovieList> {
        val list = arrayListOf<HomeMovieList>()
        val netflixUpcoming =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "NetflixUpcoming",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 0,
                title = netflixUpcoming.data.title,
                movieList = netflixUpcoming.data.movies.map { it.toMovie() }
            )
        )
        val netflixTop10 =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "NetflixTop10",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 1,
                title = netflixTop10.data.title,
                movieList = netflixTop10.data.movies.map { it.toMovie() }
            )
        )
        val netflixTrending =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "NetflixTrending",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 2,
                title = netflixTrending.data.title,
                movieList = netflixTrending.data.movies.map { it.toMovie() }
            )
        )
        val netflixPopular =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "NetflixPopular",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 3,
                title = netflixPopular.data.title,
                movieList = netflixPopular.data.movies.map { it.toMovie() }
            )
        )
        return list
    }

    override suspend fun getAmazonData(): List<HomeMovieList> {
        val list = arrayListOf<HomeMovieList>()
        val amazonUpcoming =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "AmazonUpcoming",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 0,
                title = amazonUpcoming.data.title,
                movieList = amazonUpcoming.data.movies.map { it.toMovie() }
            )
        )
        val amazonTopMovie =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "AmazonTopMovies",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 1,
                title = amazonTopMovie.data.title,
                movieList = amazonTopMovie.data.movies.map { it.toMovie() }
            )
        )
        val amazonLatestMovie =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "AmazonLatestMovies",
                    "page" to 1,
                    "page_size" to 10
                )
            )
                .getResponse()
        list.add(
            HomeMovieList(
                id = 2,
                title = amazonLatestMovie.data.title,
                movieList = amazonLatestMovie.data.movies.map { it.toMovie() }
            )
        )
        val amazonOriginal =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "AmazonOriginal",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 3,
                title = amazonOriginal.data.title,
                movieList = amazonOriginal.data.movies.map { it.toMovie() }
            )
        )
        return list
    }

    override suspend fun getHotstarData(): List<HomeMovieList> {
        val list = arrayListOf<HomeMovieList>()
        val hotstarTrending =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "HotstarTrending",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 0,
                title = hotstarTrending.data.title,
                movieList = hotstarTrending.data.movies.map { it.toMovie() }
            )
        )
        val hotstatNew =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "HotstarNew",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 1,
                title = hotstatNew.data.title,
                movieList = hotstatNew.data.movies.map { it.toMovie() }
            )
        )
        val hotstarSpecials =
            tmdbService.getMoviesByType(
                params = mapOf(
                    "type" to "HotstarSpecials",
                    "page" to 1,
                    "page_size" to 10
                )
            ).getResponse()
        list.add(
            HomeMovieList(
                id = 2,
                title = hotstarSpecials.data.title,
                movieList = hotstarSpecials.data.movies.map { it.toMovie() }
            )
        )
        return list
    }
}

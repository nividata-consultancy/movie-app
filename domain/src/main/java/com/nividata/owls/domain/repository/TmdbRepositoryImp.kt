package com.nividata.owls.domain.repository

import com.nividata.owls.domain.core.model.WatchProviderData
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.data.api.TmdbService
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

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        return tmdbService.getMovieDetails(id).getResponse()
    }

    override suspend fun getMovieCastCrew(id: Int): CastCrew {
        return tmdbService.getMovieCastCrew(id).getResponse()
    }

    override suspend fun getMovieRecommendations(id: Int, page: Int, pageSize: Int): HomeMovieList {
        val recommendations =
            tmdbService.getMovieRecommendations(id, mapOf("page" to page)).getResponse()
        return HomeMovieList(
            id = 1,
            title = "Recommendations",
            movieList = recommendations.results.map { it.toMovie() },
            page = recommendations.page,
            totalPages = recommendations.totalPages,
        )
    }

    override suspend fun getMovieSimilar(id: Int, page: Int, pageSize: Int): HomeMovieList {
        return try {
            val similar =
                tmdbService.getMovieSimilar(id, mapOf("page" to page)).getResponse()
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = similar.results.map { it.toMovie() },
                page = similar.page,
                totalPages = similar.totalPages,
            )
        } catch (e: Exception) {
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = emptyList(),
                page = 1,
                totalPages = 1,
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

    override suspend fun getTvRecommendations(id: Int, page: Int, pageSize: Int): HomeMovieList {
        val recommendations =
            tmdbService.getTvRecommendations(id, mapOf("page" to page)).getResponse()
        return HomeMovieList(
            id = 1,
            title = "Recommendations",
            movieList = recommendations.results.map { it.toMovie() },
            page = recommendations.page,
            totalPages = recommendations.totalPages,
        )
    }

    override suspend fun getTvSimilar(id: Int, page: Int, pageSize: Int): HomeMovieList {
        return try {
            val similar =
                tmdbService.getTvSimilar(id, mapOf("page" to page)).getResponse()
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = similar.results.map { it.toMovie() },
                page = similar.page,
                totalPages = similar.totalPages,
            )
        } catch (e: Exception) {
            HomeMovieList(
                id = 1,
                title = "Similar",
                movieList = emptyList(),
                page = 1,
                totalPages = 1,
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

    override suspend fun getMovieList(
        id: Int,
        type: String,
        categoryType: String,
        page: Int,
        pageSize: Int
    ): HomeMovieList {
        return if (type == "movie") {
            if (categoryType == "recommendations")
                getMovieRecommendations(id = id, page = page, pageSize = pageSize)
            else
                getMovieSimilar(id = id, page = page, pageSize = pageSize)
        } else {
            if (categoryType == "recommendations")
                getTvRecommendations(id = id, page = page, pageSize = pageSize)
            else
                getTvSimilar(id = id, page = page, pageSize = pageSize)
        }
    }
}

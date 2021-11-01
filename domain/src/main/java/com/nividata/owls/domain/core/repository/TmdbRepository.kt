package com.nividata.owls.domain.core.repository

import com.nividata.owls.domain.core.model.WatchProviderData
import com.nividata.owls.domain.model.*
import javax.inject.Singleton

@Singleton
interface TmdbRepository {
    suspend fun getMovieDetails(id: Int): MovieDetails
    suspend fun getMovieCastCrew(id: Int): CastCrew
    suspend fun getMovieRecommendations(id: Int, page: Int = 1, pageSize: Int = 10): HomeMovieList
    suspend fun getMovieSimilar(id: Int): HomeMovieList
    suspend fun getMovieExternalIds(id: Int): ExternalIds
    suspend fun getMovieWatchProviders(id: Int): WatchProviderData

    suspend fun getTvDetails(id: Int): TvDetails
    suspend fun getTvCastCrew(id: Int): CastCrew
    suspend fun getTvRecommendations(id: Int, page: Int = 1, pageSize: Int = 10): HomeMovieList
    suspend fun getTvSimilar(id: Int): HomeMovieList
    suspend fun getTvExternalIds(id: Int): ExternalIds
    suspend fun getTvWatchProviders(id: Int): WatchProviderData

    suspend fun getMovieList(id: Int, type: String, page: Int, pageSize: Int): HomeMovieList
}

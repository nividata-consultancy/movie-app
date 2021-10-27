package com.nividata.owls.domain.core.repository

import com.nividata.owls.domain.data.model.response.GenreList
import com.nividata.owls.domain.data.model.response.MovieList
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.domain.data.model.response.TvListResponse
import com.nividata.owls.domain.model.*
import javax.inject.Singleton

@Singleton
interface TmdbRepository {
    suspend fun getHomeMovies(): List<HomeMovieList>
    suspend fun getUpcomingMovies(): MovieListResponse
    suspend fun getAvailableMovies(): MovieListResponse
    suspend fun getPopularMovies(): MovieListResponse
    suspend fun getTrendingMovies(): MovieListResponse
    suspend fun getPopularMoviesByNetwork(networks: String): TvListResponse

    suspend fun getHomeTv(): List<HomeTvList>
    suspend fun getPopularTv(): TvListResponse
    suspend fun getTvGenre(): GenreList
    suspend fun getMovieGenre(): GenreList
    suspend fun getGenre(): GenreTypeWise
    suspend fun getPeople(): PeopleList

    suspend fun getMovieDetails(id: Int): MovieDetails
    suspend fun getCastCrew(id: Int): CastCrew
    suspend fun getMovieRecommendations(id: Int): HomeMovieList
}

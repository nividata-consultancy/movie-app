package com.nividata.movie_time.domain.data.api

import com.nividata.movie_time.domain.data.model.response.MovieListResponse
import com.nividata.movie_time.domain.data.model.response.TvListResponse
import com.nividata.movie_time.domain.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface TmdbService {
    @GET("3/movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<MovieDetails>

    @GET("3/movie/{id}/credits")
    suspend fun getMovieCastCrew(@Path("id") id: Int): Response<CastCrew>

    @GET("3/movie/{id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("id") id: Int,
        @QueryMap options: Map<String, Int>
    ): Response<MovieListResponse>

    @GET("3/movie/{id}/similar")
    suspend fun getMovieSimilar(
        @Path("id") id: Int,
        @QueryMap options: Map<String, Int>
    ): Response<MovieListResponse>

    @GET("3/movie/{id}/external_ids")
    suspend fun getMovieExternalIds(@Path("id") id: Int): Response<ExternalIds>

    @GET("3/movie/{id}/watch/providers")
    suspend fun getMovieWatchProviders(@Path("id") id: Int): Response<WatchProviders>

    @GET("3/tv/{id}")
    suspend fun getTvDetails(@Path("id") id: Int): Response<TvDetails>

    @GET("3/tv/{id}/credits")
    suspend fun getTvCastCrew(@Path("id") id: Int): Response<CastCrew>

    @GET("3/tv/{id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("id") id: Int,
        @QueryMap options: Map<String, Int>
    ): Response<TvListResponse>

    @GET("3/tv/{id}/similar")
    suspend fun getTvSimilar(
        @Path("id") id: Int,
        @QueryMap options: Map<String, Int>
    ): Response<TvListResponse>

    @GET("3/tv/{id}/external_ids")
    suspend fun getTvExternalIds(@Path("id") id: Int): Response<ExternalIds>

    @GET("3/tv/{id}/watch/providers")
    suspend fun getTvWatchProviders(@Path("id") id: Int): Response<WatchProviders>
}

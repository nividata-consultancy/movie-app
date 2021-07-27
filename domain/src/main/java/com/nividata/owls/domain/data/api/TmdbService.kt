package com.nividata.owls.domain.data.api

import com.nividata.owls.domain.data.model.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TmdbService {

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieListResponse>

    @GET("3/movie/now_playing")
    suspend fun getAvailableMovies(): Response<MovieListResponse>

    @GET("3/trending/movie/week")
    suspend fun getTrendingMovies(): Response<MovieListResponse>

    @GET("3/discover/movie")
    suspend fun getPopularMoviesByNetwork(@QueryMap options: Map<String, String>): Response<MovieListResponse>
}

package com.nividata.owls.domain.data.api

import com.nividata.owls.domain.data.model.response.GenreList
import com.nividata.owls.domain.data.model.response.MovieListResponse
import com.nividata.owls.domain.data.model.response.TvListResponse
import com.nividata.owls.domain.model.PeopleList
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

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): Response<MovieListResponse>

    @GET("3/discover/movie")
    suspend fun getPopularMoviesByNetwork(@QueryMap options: Map<String, String>): Response<MovieListResponse>

    @GET("3/tv/popular")
    suspend fun getPopularTv(): Response<TvListResponse>

    @GET("3/discover/tv")
    suspend fun getPopularTvByNetwork(@QueryMap options: Map<String, String>): Response<TvListResponse>

    @GET("3/genre/tv/list")
    suspend fun getTvGenre(): Response<GenreList>

    @GET("3/genre/movie/list")
    suspend fun getMovieGenre(): Response<GenreList>

    @GET("3/person/popular")
    suspend fun getPeople(): Response<PeopleList>
}

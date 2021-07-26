package com.nividata.owls.domain.data.api

import com.nividata.owls.domain.data.model.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface TmdbService {

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieListResponse>

    @GET("3/movie/now_playing")
    suspend fun getAvailableMovies(): Response<MovieListResponse>
}

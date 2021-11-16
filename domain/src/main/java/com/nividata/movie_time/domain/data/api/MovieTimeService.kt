package com.nividata.movie_time.domain.data.api

import com.nividata.movie_time.domain.data.model.response.MovieList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface MovieTimeService {
    @POST("get-movie-list/")
    suspend fun getMoviesByType(@Body params: Map<String, Any>): Response<MovieList>
}
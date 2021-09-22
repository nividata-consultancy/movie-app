package com.nividata.owls.domain.data.api

import com.nividata.owls.domain.data.model.response.MovieList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OwlsService {
    @POST("get-movie-list/")
    suspend fun getMoviesByType(@Body params: Map<String, String>): Response<MovieList>
}
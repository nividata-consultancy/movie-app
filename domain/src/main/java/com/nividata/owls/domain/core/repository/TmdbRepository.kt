package com.nividata.owls.domain.core.repository

import com.nividata.owls.domain.data.model.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface TmdbRepository {
    fun getUpcomingMovies(): Flow<ResponseResult<MovieListResponse>>
}

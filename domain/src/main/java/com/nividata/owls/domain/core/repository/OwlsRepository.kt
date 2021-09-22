package com.nividata.owls.domain.core.repository

import com.nividata.owls.domain.model.HomeMovieList
import javax.inject.Singleton

@Singleton
interface OwlsRepository {
    suspend fun getNetflixData(): List<HomeMovieList>
    suspend fun getAmazonData(): List<HomeMovieList>
    suspend fun getHotstarData(): List<HomeMovieList>
}

package com.nividata.movie_time.di

import com.nividata.movie_time.domain.core.repository.MovieTimeRepository
import com.nividata.movie_time.domain.core.repository.TmdbRepository
import com.nividata.movie_time.domain.repository.MovieTimeRepositoryImp
import com.nividata.movie_time.domain.repository.TmdbRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @ExperimentalCoroutinesApi
    @Binds
//    @RemoteRepository
    fun tmdbRepository(tmdbRepository: TmdbRepositoryImp): TmdbRepository

    @Binds
    @ExperimentalCoroutinesApi
    fun movieTimeRepository(movieTimeRepository: MovieTimeRepositoryImp): MovieTimeRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository

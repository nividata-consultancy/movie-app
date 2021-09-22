package com.nividata.owls.di

import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.repository.OwlsRepositoryImp
import com.nividata.owls.domain.repository.TmdbRepositoryImp
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
    fun imdbRepository(tmdbRepository: TmdbRepositoryImp): TmdbRepository

    @Binds
    @ExperimentalCoroutinesApi
    fun owlsRepository(owlsRepository: OwlsRepositoryImp): OwlsRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository

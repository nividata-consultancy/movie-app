package com.nividata.owls.di

import com.nividata.owls.domain.core.repository.TmdbRepository
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
    fun notyRemoteNoteRepository(remoteRepository: TmdbRepositoryImp): TmdbRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository

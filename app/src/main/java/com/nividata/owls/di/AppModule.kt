package com.nividata.owls.di

import android.app.Application
import com.nividata.owls.domain.core.preference.PreferenceManager
import com.nividata.owls.domain.core.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePreferenceManager(application: Application): PreferenceManager {
        return PreferenceManagerImpl(application)
    }

    @Singleton
    @Provides
    fun provideSessionManager(application: Application): SessionManager {
        return SessionManagerImpl(application)
    }
}

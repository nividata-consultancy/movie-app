package com.nividata.movie_time.di

import com.nividata.movie_time.domain.core.utils.moshi
import com.nividata.movie_time.domain.data.Constant
import com.nividata.movie_time.domain.data.api.OwlsService
import com.nividata.movie_time.domain.data.api.TmdbService
import com.nividata.movie_time.domain.data.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

    private val owlsRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.OWLS_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))


    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

    @Provides
    fun provideTmdbService(authInterceptor: AuthInterceptor): TmdbService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(TmdbService::class.java)
    }

    @Provides
    fun provideOwlsAuthService(): OwlsService {
        return owlsRetrofitBuilder
            .client(okHttpClientBuilder.build())
            .build()
            .create(OwlsService::class.java)
    }
}

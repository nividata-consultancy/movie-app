package com.nividata.owls.di

import com.nividata.owls.domain.core.utils.moshi
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.data.api.TmdbService
import com.nividata.owls.domain.data.interceptor.AuthInterceptor
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


    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

    @Provides
    fun provideNotyService(authInterceptor: AuthInterceptor): TmdbService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(TmdbService::class.java)
    }

//    @Provides
//    fun provideNotyAuthService(): NotyAuthService {
//        return baseRetrofitBuilder
//            .client(okHttpClientBuilder.build())
//            .build()
//            .create(NotyAuthService::class.java)
//    }
}

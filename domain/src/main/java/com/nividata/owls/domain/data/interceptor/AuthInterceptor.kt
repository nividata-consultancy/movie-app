package com.nividata.owls.domain.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interceptor which adds authorization token in header.
 */
@Singleton
class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder().apply {
            header(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDFjYjI2MTNkOGYxODE5MDAyODkyODIwYzQ5Yzg4ZCIsInN1YiI6IjYwZTU3MWM0ODNlZTY3MDA1ZGU1ZWYxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AoAnnujUJbVnstTL1ykfcLElvAoruXbVi-2JWMXC0gA"
            )
        }.build()
        return chain.proceed(authRequest)
    }
}

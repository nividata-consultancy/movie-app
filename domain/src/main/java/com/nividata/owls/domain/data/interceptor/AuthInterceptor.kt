package com.nividata.owls.domain.data.interceptor

import com.nividata.owls.domain.core.session.SessionManager
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor which adds authorization token in header.
 */
@Singleton
class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder().apply {
            sessionManager.getToken()?.let { header("Authorization", "Bearer $it") }
        }.build()
        return chain.proceed(authRequest)
    }
}

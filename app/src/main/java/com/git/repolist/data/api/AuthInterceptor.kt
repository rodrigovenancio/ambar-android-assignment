package com.git.repolist.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * A {@see RequestInterceptor} that adds the required headers to requests
 */
class AuthInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/vnd.github.v3+json").build()
        return chain.proceed(request)
    }
}
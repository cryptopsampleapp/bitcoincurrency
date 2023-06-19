package com.aldi.coin.domain.model.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val orig = chain.request()
        val processed = orig.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept-Encoding","gzip")
            .build()
        return chain.proceed(processed)
    }
}
package com.nasinha.digitalspace.apod.network

import okhttp3.Interceptor
import okhttp3.Response

class NetwokInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val apikey = PRIVATE_KEY

        var request = chain.request()
        val originalHttpUrl = request.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, apikey)
            .build()

        val requestBuilder = request.newBuilder().url(url)
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {

        private const val API_KEY = "api_key"
        private const val PRIVATE_KEY = "gAFkqlGusIgjAzp0UrMhjbkqYfzlyq5jRL8BeBjv"

    }
}
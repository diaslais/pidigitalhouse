package com.renan.digitalspace.apod.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtilsApod {
    companion object {


        private lateinit var retrofit: Retrofit
        private const val baseUrl = "https://api.nasa.gov/"

       fun getRetroFitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }


    }
}
package com.renan.digitalspace.apod

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtilsApod {
    companion object {


        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://api.nasa.gov/"

       private fun getRetroFitInstance(): Retrofit {

            var httpClient = OkHttpClient.Builder()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>):T{
            return getRetroFitInstance().create(serviceClass)
        }
    }
}
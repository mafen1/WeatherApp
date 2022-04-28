package com.example.myweather.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

object ApiClient{

    private val baseUrl = "http://api.openweathermap.org/"
    private var retrofit: Retrofit? = null

    private fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                // change format Json object
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    // просим сервер дать данные
    fun getApiService() = getClient(baseUrl).create(ApiService::class.java) ?: throw IllegalAccessException("problems in ApiClient")
}
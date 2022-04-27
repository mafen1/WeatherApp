package com.example.myweather.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val baseUrl = "http://api.openweathermap.org/"
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String= "http://api.openweathermap.org/"): Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                                    // change format Json object
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    // просим сервер дать данные
    fun getApiService() = getClient(baseUrl).create(ApiService::class.java)
}
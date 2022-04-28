package com.example.myweather.data.api

import com.example.myweather.core.ImmutableValues
import com.example.myweather.data.models.ResponseWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun fetchWeather(
        @Query("q") userCity: String,
        @Query("appid") idApp: String = ImmutableValues.API_ID,
        @Query("units") units: String = ImmutableValues.units,
        @Query("lang") language: String = ImmutableValues.language
    ): Response<ResponseWeather>
}
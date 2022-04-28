package com.example.myweather.domain.repository

import com.example.myweather.data.models.ResponseWeather
import retrofit2.Response

interface Repository {
    suspend fun getTemperature(userCity: String): Response<ResponseWeather>
}
package com.example.myweather.domain.repository

import com.example.myweather.data.api.ApiService
import com.example.myweather.data.models.ResponseWeather
import retrofit2.Response

interface Repository {

    suspend fun getTemperature(userCity: String): Response<ResponseWeather>

    class RepositoryImpl: Repository {
        override suspend fun getTemperature(userCity: String): Response<ResponseWeather> = ApiService.ApiServiceImpl().fetchWeather(userCity)
    }

}
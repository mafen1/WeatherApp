package com.example.myweather.data.repository

import com.example.myweather.data.api.ApiService
import com.example.myweather.data.models.ResponseWeather
import com.example.myweather.domain.repository.Repository
import retrofit2.Response

class RepositoryImpl : Repository {

    override suspend fun getTemperature(userCity: String): Response<ResponseWeather> =
        ApiService.ApiServiceImpl().fetchWeather(userCity)
}
package com.example.myweather.data.repository

import com.example.myweather.data.api.ApiService
import com.example.myweather.data.models.ResponseWeather
import com.example.myweather.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getTemperature(userCity: String): Response<ResponseWeather> = apiService.fetchWeather(userCity)
//        ApiService.ApiServiceImpl().fetchWeather(userCity)
}
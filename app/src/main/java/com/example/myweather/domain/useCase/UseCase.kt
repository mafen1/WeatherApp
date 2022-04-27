package com.example.myweather.domain.useCase

import com.example.myweather.domain.repository.Repository

class UseCase(private val repository: Repository) {

    suspend fun getTemperature(userCity: String) = repository.getTemperature(userCity)
}
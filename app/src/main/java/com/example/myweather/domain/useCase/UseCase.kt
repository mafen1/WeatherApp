package com.example.myweather.domain.useCase

import com.example.myweather.domain.repository.Repository
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    suspend fun getTemperature(userCity: String) = repository.getTemperature(userCity)
}
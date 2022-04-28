package com.example.myweather.di

import com.example.myweather.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    @Named("weather")
    fun provideBaseUrl1() = "http://api.openweathermap.org/"

    @Provides
    @Singleton
    @Named("yandex")
    fun provideBaseUrl2() = "https://yandex.ru"

    @Provides
    @Singleton
    fun provideRetrofitClient(@Named("weather") baseUrl : String) : Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService =
        retrofit.create(ApiService::class.java)
}
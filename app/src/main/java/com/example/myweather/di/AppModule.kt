package com.example.myweather.di

import com.example.myweather.data.repository.RepositoryImpl
import com.example.myweather.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsRepository(repository: RepositoryImpl) : Repository
}
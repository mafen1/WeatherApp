package com.example.myweather.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.domain.useCase.UseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val useCase: UseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
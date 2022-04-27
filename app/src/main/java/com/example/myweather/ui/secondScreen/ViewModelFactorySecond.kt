package com.example.myweather.ui.secondScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.domain.useCase.UseCase
import com.example.myweather.ui.mainScreen.MainViewModel

class ViewModelFactorySecond(private val useCase: UseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(SecondViewModel::class.java)){
            SecondViewModel(this.useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
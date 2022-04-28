package com.example.myweather.ui.secondScreen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.core.makeSnackBarMessage
import com.example.myweather.data.models.ResponseWeather
import com.example.myweather.domain.useCase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var _weather: MutableLiveData<ResponseWeather> = MutableLiveData()
    var weather: LiveData<ResponseWeather> = _weather

    fun fillInfo(userCity: String, view: View) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.getTemperature(userCity)

            if (response.isSuccessful) {
                _weather.postValue(response.body())
            } else {
                makeSnackBarMessage(view, "Город не найден")
            }
        }
    }
}
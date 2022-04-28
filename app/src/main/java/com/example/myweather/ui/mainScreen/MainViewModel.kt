package com.example.myweather.ui.mainScreen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.core.Extension1
import com.example.myweather.domain.useCase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val exception = Extension1()

    private var _listTemperature: MutableLiveData<Double> = MutableLiveData()
    var listTemperature: LiveData<Double> = _listTemperature


    fun temperatureInThisCity(userCity: String, view: View) {
        viewModelScope.launch(Dispatchers.IO) {

            val getClient = useCase.getTemperature(userCity)
            if (getClient.isSuccessful) {
                _listTemperature.postValue(getClient.body()?.main?.temp!!)
            } else {
                exception.makeSnackBarMessage(view, "Город не найден")
            }
        }
    }
}
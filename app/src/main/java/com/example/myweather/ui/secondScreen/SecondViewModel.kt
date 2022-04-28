package com.example.myweather.ui.secondScreen

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.core.Extension
import com.example.myweather.core.ImmutableValues
import com.example.myweather.domain.useCase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(
    private val useCase: UseCase
) : ViewModel() {
    private val exception = Extension()

    private var _temperature: MutableLiveData<Double> = MutableLiveData()
    var temperature: LiveData<Double> = _temperature

    private var _cloudinessParameter: MutableLiveData<Int> = MutableLiveData()
    var cloudinessParameter: LiveData<Int> = _cloudinessParameter

    private var _description: MutableLiveData<String> = MutableLiveData()
    var description: LiveData<String> = _description

    private var _minTemperature: MutableLiveData<Double> = MutableLiveData()
    var minTemperature: LiveData<Double> = _minTemperature

    private var _maxTemperature: MutableLiveData<Double> = MutableLiveData()
    var maxTemperature: LiveData<Double> = _maxTemperature


    fun fillInfo(userCity: String, view: View) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.getTemperature(userCity)

            if (response.isSuccessful) {
                _temperature.postValue(
                    response.body()?.main?.temp ?: throw IllegalStateException("not info")
                )
                Log.d(ImmutableValues.TAG, _temperature.value.toString())
                _cloudinessParameter.postValue(
                    response.body()?.clouds?.all ?: throw IllegalStateException("not info")
                )
                _description.postValue(
                    response.body()?.weather?.get(0)?.description ?: throw IllegalStateException("not info")
                )
                Log.d(ImmutableValues.TAG, _description.value.toString())
                _minTemperature.postValue(
                    response.body()?.main?.tempMin ?: throw IllegalStateException("not info")
                )
                _maxTemperature.postValue(
                    response.body()?.main?.tempMax ?: throw IllegalStateException("not info")
                )
            } else {
                exception.makeSnackBarMessage(view, "Город не найден")
            }
        }
    }
}
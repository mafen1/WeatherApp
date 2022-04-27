package com.example.myweather.ui.mainScreen

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.core.Extension
import com.example.myweather.core.ImmutableValues
import com.example.myweather.domain.useCase.UseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

//@HiltViewModel
class MainViewModel (
    private val useCase: UseCase
): ViewModel(){

    private val exception = Extension()

    private var _listTemperature: MutableLiveData<Double> = MutableLiveData()
    var listTemperature :LiveData<Double> = _listTemperature


    fun temperatureInThisCity(userCity: String, view: View){
        viewModelScope.launch(Dispatchers.IO) {

            val getClient = useCase.getTemperature(userCity)
            if (getClient.isSuccessful){
                _listTemperature.postValue(getClient.body()?.main?.temp!!)
            }else{
                exception.makeSnackBarMessage(view, "Город не найден")
            }
        }
    }
}
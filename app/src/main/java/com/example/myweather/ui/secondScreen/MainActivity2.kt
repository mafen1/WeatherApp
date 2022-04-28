package com.example.myweather.ui.secondScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.core.updateText
import com.example.myweather.data.models.ResponseWeather
import com.example.myweather.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserves()
        initView()
    }

    private fun initView() {
        val currentCity = intent.getStringExtra("edText")
        viewModel.fillInfo(currentCity!!, binding.root)

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    private fun initObserves() {
        viewModel.weather.observe(this@MainActivity2) { updateUi(it) }
    }

    private fun updateUi(it: ResponseWeather?) {
        updateText(binding.tvParameterTemperature, it?.main?.temp.toString())
        updateText(binding.tvParameterCloud, it?.clouds?.all.toString())
        updateText(binding.tvParameterDescription, it?.weather?.get(0)?.description.toString())
        updateText(binding.tvParameterMinTemperature, it?.main?.tempMin.toString())
        updateText(binding.tvParameterMaxTemperature, it?.main?.tempMax.toString())
    }
}
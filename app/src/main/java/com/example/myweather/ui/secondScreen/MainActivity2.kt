package com.example.myweather.ui.secondScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.myweather.R
import com.example.myweather.core.Extension
import com.example.myweather.core.ImmutableValues
import com.example.myweather.data.repository.RepositoryImpl
import com.example.myweather.databinding.ActivityMain2Binding
import com.example.myweather.domain.useCase.UseCase
import com.example.myweather.ui.mainScreen.MainViewModel
import com.example.myweather.ui.mainScreen.ViewModelFactory

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    private val repository = RepositoryImpl()
    private val viewModel: SecondViewModel by viewModels {
        ViewModelFactorySecond(UseCase(repository))
    }
    private val extension = Extension()

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

        binding.btnFinsh.setOnClickListener {
            finish()
        }
    }

    private fun initObserves() {

        viewModel.apply {
            temperature.observe(this@MainActivity2) {
                extension.updateText(binding.tvParameterTemperature, it.toString())
            }
            cloudinessParametr.observe(this@MainActivity2) {
                extension.updateText(binding.tvParameterCloud, it.toString())
            }
            description.observe(this@MainActivity2) {
                extension.updateText(binding.tvParameterDescription, it.toString())

            }
            minTemperature.observe(this@MainActivity2) {
                extension.updateText(binding.tvParameterMinTemperature, it.toString())

            }
            maxTemperature.observe(this@MainActivity2) {
                extension.updateText(binding.tvParameterMaxTemperature, it.toString())

            }
        }

    }
}
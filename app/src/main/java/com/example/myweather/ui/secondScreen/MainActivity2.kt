package com.example.myweather.ui.secondScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.core.Extension1
import com.example.myweather.core.updateText
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

        viewModel.apply {
            temperature.observe(this@MainActivity2) {
                updateText(binding.tvParameterTemperature, it.toString())
            }
            cloudinessParameter.observe(this@MainActivity2) {
                updateText(binding.tvParameterCloud, it.toString())
            }
            description.observe(this@MainActivity2) {
                updateText(binding.tvParameterDescription, it.toString())

            }
            minTemperature.observe(this@MainActivity2) {
                updateText(binding.tvParameterMinTemperature, it.toString())
            }
            maxTemperature.observe(this@MainActivity2) {
                updateText(binding.tvParameterMaxTemperature, it.toString())
            }
        }
    }
}
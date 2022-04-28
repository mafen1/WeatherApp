package com.example.myweather.ui.mainScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.core.makeSnackBarMessage
import com.example.myweather.core.updateText
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.ui.secondScreen.MainActivity2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        binding.btnMore.visibility = View.GONE
    }

    private fun initView() {
        binding.btnSearchCity.setOnClickListener {
            if (binding.edFindCity.text.isNotEmpty()) {
                binding.btnMore.visibility = View.VISIBLE
                viewModel.temperatureInThisCity(binding.edFindCity.text.toString(), binding.root)
                initObserver()
            } else {
                makeSnackBarMessage(binding.root, "Пожалуйста введите город!!")
                binding.btnMore.visibility = View.GONE
            }
        }

        binding.btnMore.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("edText", binding.edFindCity.text.toString())
            startActivity(i)
        }

    }

    private fun initObserver() {
        viewModel.listTemperature.observe(this) {
            updateText(binding.tvTemperature, it.toString())
        }
    }
}
package com.example.myweather.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myweather.core.Extension
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.domain.repository.Repository
import com.example.myweather.domain.useCase.UseCase

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val repository = Repository.RepositoryImpl()
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(UseCase(repository))
    }
    private val extension = Extension()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserves()
    }

    private fun initObserves() {
        val edText = binding.editTextTextPersonName.text

        binding.btnSearchCity.setOnClickListener {
            if (edText.isNotEmpty()) {

                viewModel.temperatureInThisCity(edText.toString(), binding.root)

                viewModel.listTemperature.observe(this) {
                    extension.updateText(binding.tvTemperature, it.toString())
                }
            } else {
                extension.makeSnackBarMessage(
                    binding.root,
                    "Пожалуйста введите город!!"
                )
            }
        }
    }
}
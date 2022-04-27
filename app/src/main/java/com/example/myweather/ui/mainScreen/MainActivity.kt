package com.example.myweather.ui.mainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myweather.core.Extension
import com.example.myweather.data.repository.RepositoryImpl
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.domain.repository.Repository
import com.example.myweather.domain.useCase.UseCase
import com.example.myweather.ui.secondScreen.MainActivity2

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val repository = RepositoryImpl()
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(UseCase(repository))
    }
    private val extension = Extension()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.btnSearchCity.setOnClickListener {
            if (binding.edFindCity.text.isNotEmpty()) {
                viewModel.temperatureInThisCity(binding.edFindCity.text.toString(), binding.root)
                initObserver()
            } else {
                extension.makeSnackBarMessage(
                    binding.root,
                    "Пожалуйста введите город!!"
                )
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
            extension.updateText(binding.tvTemperature, it.toString())
        }
    }
}
package com.example.sunnyweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunnyweather.data.repositories.WeatherRepository
import com.example.sunnyweather.ui.MainActivity
import kotlinx.coroutines.launch

class LocationWeatherViewModel: ViewModel() {
    private val weatherRepository by lazy { WeatherRepository() }
    val location = MutableLiveData<String>()
    val weatherData = MutableLiveData<String>()

    init {
        location.observeForever { location -> // 為什麼要用 observeForever
            viewModelScope.launch {
                try {
                    val locationWeather = weatherRepository.getWeatherByLocation(location)
                    weatherData.postValue(locationWeather.toString())
                } catch (exception: Exception) {
                    weatherData.postValue("Exception $exception in ViewModel")
                }
            }
        }
    }
}
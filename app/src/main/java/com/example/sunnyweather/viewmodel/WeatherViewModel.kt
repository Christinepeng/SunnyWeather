package com.example.sunnyweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunnyweather.data.repositories.WeatherRepository
import com.example.sunnyweather.model.WeatherData
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherData?>(null) // 用於存儲天氣數據
    val weatherData: LiveData<WeatherData?> = _weatherData

//    private val _isLoading = MutableLiveData<Boolean>(false)
//    val isLoading: LiveData<Boolean>
//        get() = _isLoading

    fun getWeather(location: String) {
        viewModelScope.launch {
            Log.e("xd", "ViewModel getWeather launch")
            Log.e("xd", "ViewModel getWeather launch")
//            _isLoading.postValue(true)
            val weather = weatherRepository.getWeatherByLocation(location)
            _weatherData.postValue(weather)
            Log.e("xd", "ViewModel getWeather finish")
//            _isLoading.postValue(false)
        }
    }
}
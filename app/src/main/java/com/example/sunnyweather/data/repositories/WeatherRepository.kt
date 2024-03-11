package com.example.sunnyweather.data.repositories

import android.util.Log
import com.example.sunnyweather.data.services.WeatherService
import com.example.sunnyweather.model.WeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val weatherService: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tomorrow.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    suspend fun getWeatherByLocation(location: String): WeatherData? {
        return try {
            Log.e("xd", "send request $location")
            val response = weatherService.getWeatherByLocation(location, "hrWCevltc3xfWzjklzMpoV8dsRKQv227")
            Log.e("xd", "Receive response $response")
            if (response.isSuccessful) {
                response.body()
            } else {
                null // or handle exceptions as needed
            }
        } catch (e: Exception) {
            Log.e("xd", "exception $e")
            null // or handle exceptions as needed
        }
    }
}
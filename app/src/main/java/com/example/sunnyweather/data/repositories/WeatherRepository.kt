package com.example.sunnyweather.data.repositories

import android.util.Log
import com.example.sunnyweather.data.services.WeatherService
import com.example.sunnyweather.model.WeatherData
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant

class WeatherRepository {
    val gson = GsonBuilder()
        .registerTypeAdapter(Instant::class.java, JsonDeserializer { json, _, _ ->
            Instant.parse(json.asJsonPrimitive.asString)
        })
        .create()

    private val weatherService: WeatherService by lazy {
        Log.e("xd", "build Retrofit")
        Retrofit.Builder()
            .baseUrl("https://api.tomorrow.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
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
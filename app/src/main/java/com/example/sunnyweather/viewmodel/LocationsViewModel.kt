package com.example.sunnyweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationsViewModel: ViewModel() {
    private val _locations = MutableLiveData<List<String>>()
    val locations: LiveData<List<String>> = _locations

    fun addLocation(location: String) {
        val currentList = _locations.value ?: emptyList()
        if (!currentList.contains(location)) {
            _locations.value = currentList + location
        }
    }
}
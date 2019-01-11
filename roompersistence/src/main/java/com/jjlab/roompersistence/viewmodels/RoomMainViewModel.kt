package com.jjlab.roompersistence.viewmodels

import androidx.lifecycle.*
import com.jjlab.roompersistence.data.WeatherEntity
import com.jjlab.roompersistence.data.WeatherRepository

class RoomMainViewModel internal constructor(private val weatherRepository: WeatherRepository) : ViewModel(), LifecycleObserver {

    private val TAG by lazy { RoomMainViewModel::class.java.simpleName }

    private val weatherData = MutableLiveData<List<WeatherEntity>>()

    init {
        weatherData = weatherRepository.getWeatherReport()
    }
}
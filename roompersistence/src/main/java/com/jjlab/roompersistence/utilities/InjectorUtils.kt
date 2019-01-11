package com.jjlab.roompersistence.utilities

import android.content.Context
import com.jjlab.roompersistence.data.WeatherDatabase
import com.jjlab.roompersistence.data.WeatherRepository
import com.jjlab.roompersistence.viewmodels.RoomMainViewModelFactory

object InjectorUtils {

    private fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepository.getInstance(WeatherDatabase.getInstance(context).weatherDao())
    }

    internal fun provideWeatherViewModelFactory(context: Context): RoomMainViewModelFactory {
        val repo = getWeatherRepository(context)
        return RoomMainViewModelFactory(repo)
    }
}
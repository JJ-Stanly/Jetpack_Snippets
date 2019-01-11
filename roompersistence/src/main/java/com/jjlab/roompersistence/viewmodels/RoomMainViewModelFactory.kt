package com.jjlab.roompersistence.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjlab.roompersistence.data.WeatherRepository

class RoomMainViewModelFactory( private val repo: WeatherRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoomMainViewModel(repo) as T
    }
}